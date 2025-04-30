package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.AtualizarUsuarioDTO;
import com.unifor.indiestream.dto.UsuarioDTO;
import com.unifor.indiestream.model.*;
import com.unifor.indiestream.repository.*;
import com.unifor.indiestream.service.UsuarioService;
import com.unifor.indiestream.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {


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

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long userId) {
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
        usuario.setImagemUrl(dto.getImagemUrl());
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
}
