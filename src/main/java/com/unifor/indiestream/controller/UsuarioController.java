package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.AtualizarUsuarioDTO;
import com.unifor.indiestream.dto.UsuarioDTO;
import com.unifor.indiestream.model.*;
import com.unifor.indiestream.repository.*;
import com.unifor.indiestream.service.UsuarioService;
import com.unifor.indiestream.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Importe LoggerFactory
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; // Importe Value
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; // Importe MultipartFile

import java.io.IOException; // Importe IOException
import java.nio.file.Files; // Importe Files
import java.nio.file.Path; // Importe Path
import java.nio.file.Paths; // Importe Paths
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID; // Importe UUID
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class); // Adicione o logger

    @Autowired
    private UsuarioService userService;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ProfissaoRepository profissaoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${file.upload-dir}") // Injeta o diretório de upload configurado
    private String uploadDir;

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long userId) {
        // Implementar lógica para deletar a imagem de perfil associada, se houver
        // Antes de deletar o usuário, obtenha a URL da imagem e chame o método de exclusão de arquivo
        try {
            UsuarioDTO userDto = userService.getUserByIdAsDTO(userId);
            if (userDto != null && userDto.getImagemUrl() != null && !userDto.getImagemUrl().isEmpty()) {
                String imageUrl = userDto.getImagemUrl();
                // Assumindo que a URL da imagem termina com o nome do arquivo
                String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
                Path filePath = Paths.get(uploadDir, fileName);
                if (Files.exists(filePath)) {
                    Files.delete(filePath);
                    logger.info("Imagem de perfil do usuário deletada: {}", filePath);
                } else {
                    logger.warn("Imagem de perfil do usuário não encontrada para deletar: {}", filePath);
                }
            }
        } catch (Exception e) {
            logger.error("Erro ao tentar deletar a imagem de perfil do usuário {}: {}", userId, e.getMessage());
            // Continua com a exclusão do usuário mesmo que a imagem não seja deletada
        }

        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsersAsDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserByIdAsDTO(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable(value = "id") Long userId, @RequestBody AtualizarUsuarioDTO dto) {
        Usuario usuario = userService.getUserById(userId); // Obter o usuário existente

        // Atualizar campos básicos
        usuario.setNome(dto.getNome());
        if (dto.getImagemUrl() != null) {
            usuario.setImagemUrl(dto.getImagemUrl()); // Atualiza se a URL for enviada no DTO
        }
        usuario.setSobreMin(dto.getSobreMim());

        // Atualizar cidade e estado
        if (dto.getCidadeId() != null) {
            Cidade cidade = cidadeRepository.findById(dto.getCidadeId())
                    .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
            usuario.setCidade(cidade);
        }
        if (dto.getEstadoId() != null) {
            Estado estado = estadoRepository.findById(dto.getEstadoId())
                    .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
            usuario.setEstado(estado);
        }

        // Atualizar profissão
        if (dto.getProfissaoId() != null) {
            Profissao profissao = profissaoRepository.findById(dto.getProfissaoId())
                    .orElseThrow(() -> new RuntimeException("Profissão não encontrada"));
            usuario.setProfissao(profissao);
        }

        // Atualizar habilidades
        if (dto.getHabilidades() != null) {
            Set<Habilidade> habilidades = dto.getHabilidades().stream()
                    .map(id -> habilidadeRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Habilidade não encontrada: " + id)))
                    .collect(Collectors.toSet());
            usuario.setHabilidades(habilidades);
        }

        // Atualizar redes sociais
        usuario.setRedesSociais(dto.getRedesSociais());

        Usuario updatedUser = userService.updateUser(userId, usuario);
        return ResponseEntity.ok(updatedUser);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getSenha())
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getId());

                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Login bem-sucedido");
                    response.put("id", user.getId());
                    response.put("token", token);

                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Credenciais inválidas")));
    }


    @PostMapping("/upload-imagem/{userId}") // Novo endpoint para upload de imagem de perfil
    public ResponseEntity<String> uploadUserImage(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"message\": \"Por favor, selecione um arquivo para upload.\"}");
        }

        try {
            // Garante que o diretório de upload existe
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Gera um nome de arquivo único para evitar colisões
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
            Path filePath = uploadPath.resolve(uniqueFileName);

            // Salva o arquivo no diretório
            Files.copy(file.getInputStream(), filePath);

            // Constrói a URL para acessar a imagem.
            // IMPORTANTE: Ajuste esta URL para corresponder à forma como seu servidor serve arquivos estáticos.
            // Para desenvolvimento local: "http://localhost:8080/static/images/" + uniqueFileName
            // Para produção, você usaria uma URL de CDN, AWS S3, etc.
            // No seu caso, use a URL do ngrok para testes:
            String imageUrl = "https://f073-2804-29b8-518f-8dbb-8cf0-a86d-8b2c-ffba.ngrok-free.app/static/images/" + uniqueFileName;

            // Atualiza a imagemUrl do usuário no banco de dados
            Usuario usuario = userService.getUserById(userId);
            if (usuario == null) {
                // Se o usuário não for encontrado, deleta o arquivo recém-salvo
                Files.delete(filePath);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"message\": \"Usuário com ID " + userId + " não encontrado.\"}");
            }

            // Opcional: Deletar imagem antiga se existir
            if (usuario.getImagemUrl() != null && !usuario.getImagemUrl().isEmpty()) {
                try {
                    String oldFileName = usuario.getImagemUrl().substring(usuario.getImagemUrl().lastIndexOf('/') + 1);
                    Path oldFilePath = Paths.get(uploadDir, oldFileName);
                    if (Files.exists(oldFilePath)) {
                        Files.delete(oldFilePath);
                        logger.info("Imagem de perfil antiga deletada: {}", oldFilePath);
                    }
                } catch (IOException e) {
                    logger.warn("Não foi possível deletar a imagem de perfil antiga para o usuário {}: {}", userId, e.getMessage());
                }
            }


            usuario.setImagemUrl(imageUrl);
            userService.updateUser(userId, usuario); // Assume que updateUser salva no banco

            // Retorna a URL da imagem. O Flutter usará esta URL.
            return ResponseEntity.ok("{\"url\": \"" + imageUrl + "\"}"); // Retorna como JSON

        } catch (IOException e) {
            logger.error("Falha ao fazer upload da imagem de perfil para o usuário {}: {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Falha ao fazer upload da imagem: " + e.getMessage() + "\"}");
        } catch (RuntimeException e) {
            logger.error("Erro ao atualizar o usuário após o upload da imagem: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Erro ao processar o upload: " + e.getMessage() + "\"}");
        }
    }
}