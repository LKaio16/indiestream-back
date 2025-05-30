package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.ProjetoDTO;
import com.unifor.indiestream.model.Projeto;
import com.unifor.indiestream.service.ProjetoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Importe HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; // Importe MultipartFile
import org.springframework.beans.factory.annotation.Value; // Importe Value

import java.io.File; // Importe File
import java.io.IOException; // Importe IOException
import java.nio.file.Files; // Importe Files
import java.nio.file.Path; // Importe Path
import java.nio.file.Paths; // Importe Paths
import java.util.List;
import java.util.UUID; // Importe UUID

@RestController
@RequestMapping("/projetos")
@CrossOrigin(origins = "http://localhost:3000") // Mantenha o CORS
public class ProjetoController {

    private static final Logger logger = LoggerFactory.getLogger(ProjetoController.class);

    @Autowired
    private ProjetoService projetoService;

    @Value("${file.upload-dir}")
    private String uploadDir;


    // --- EXISTENTES (SEM MUDANÇAS AQUI) ---
    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> getProjetos(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return ResponseEntity.ok(projetoService.getProjetosByUserId(userId));
        }
        return ResponseEntity.ok(projetoService.getAllProjetos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> getProjetoById(@PathVariable Long id) {
        return projetoService.getProjetoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjetoDTO> createProjeto(@RequestBody Projeto projeto, @RequestParam Long usuarioCriadorId) {
        // Nenhuma mudança significativa aqui, apenas certifique-se de que o ProjetoDTO
        // tem o campo imagemUrl, que será preenchido pelo Flutter após o upload.
        return ResponseEntity.ok(projetoService.createProjeto(projeto, usuarioCriadorId));
    }
    // --- FIM DOS EXISTENTES ---

    // --- NOVO ENDPOINT PARA UPLOAD DE IMAGEM ---
    @PostMapping("/upload-imagem") // Este é o novo endpoint
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
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
            // IMPORTANTE: Isso dependerá de como seu servidor está configurado para servir arquivos estáticos.
            // Para desenvolvimento local: "http://localhost:8080/static/images/" + uniqueFileName
            // Para produção, você usaria uma URL de CDN, AWS S3, etc.
            String imageUrl = "https://f073-2804-29b8-518f-8dbb-8cf0-a86d-8b2c-ffba.ngrok-free.app/static/images/" + uniqueFileName;

            // Retorna a URL da imagem. O Flutter usará esta URL.
            return ResponseEntity.ok("{\"url\": \"" + imageUrl + "\"}"); // Retorna como JSON

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Falha ao fazer upload da imagem: " + e.getMessage() + "\"}");
        }
    }
    // --- FIM DO NOVO ENDPOINT ---

    // --- OUTROS ENDPOINTS EXISTENTES (SEM MUDANÇAS AQUI) ---
    @PutMapping("/{id}/add-pessoa/{usuarioId}")
    public ResponseEntity<ProjetoDTO> addPessoaEnvolvida(@PathVariable Long id, @PathVariable Long usuarioId) {
        return ResponseEntity.ok(projetoService.addPessoaEnvolvida(id, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id) {
        try {
            // 1. Fetch the ProjetoDTO to get the image URL
            ProjetoDTO projetoDTO = projetoService.getProjetoById(id).orElse(null);

            // 2. Check if the project exists and has an image URL
            if (projetoDTO != null && projetoDTO.getImagemUrl() != null) {
                // 3. Extract the filename from the URL
                String imageUrl = projetoDTO.getImagemUrl();
                String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);

                // 4. Construct the full file path
                Path filePath = Paths.get(uploadDir, fileName);

                // 5. Check if the file exists and delete it
                if (Files.exists(filePath)) {
                    Files.delete(filePath);
                    logger.info("Imagem do projeto deletada: {}", filePath);
                } else {
                    logger.warn("Imagem do projeto não encontrada para deletar: {}", filePath);
                }
            }

            // 6. Delete the project (regardless of image deletion success)
            projetoService.deleteProjeto(id);
            return ResponseEntity.noContent().build();

        } catch (IOException e) {
            // 7. Log any errors during image deletion
            logger.error("Erro ao deletar a imagem do projeto", e);
            // 8. Still delete the project, even if image deletion fails.
            projetoService.deleteProjeto(id);
            return ResponseEntity.noContent().build();
        }
    }


    @PutMapping("/{id}/add-solicitante/{usuarioId}")
    public ResponseEntity<ProjetoDTO> addSolicitante(@PathVariable Long id, @PathVariable Long usuarioId) {
        return ResponseEntity.ok(projetoService.addSolicitante(id, usuarioId));
    }

    @PutMapping("/{id}/remove-solicitante/{usuarioId}")
    public ResponseEntity<ProjetoDTO> removeSolicitante(
            @PathVariable Long id,
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(projetoService.removeSolicitante(id, usuarioId));
    }

    @PutMapping("/{id}/set-criador/{usuarioId}")
    public ResponseEntity<ProjetoDTO> setUsuarioCriador(@PathVariable Long id, @PathVariable Long usuarioId) {
        return ResponseEntity.ok(projetoService.setUsuarioCriador(id, usuarioId));
    }
}