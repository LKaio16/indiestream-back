package com.unifor.indiestream.controller;

import com.unifor.indiestream.model.Usuario;
import com.unifor.indiestream.repository.UsuarioRepository;
import com.unifor.indiestream.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private UsuarioService userService;

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
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Novo método para editar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable(value = "id") Long userId, @RequestBody Usuario updatedUser) {
        return ResponseEntity.ok(userService.updateUser(userId, updatedUser));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getSenha())
                .map(user -> {
                    Map<String, Object> responseBody = new HashMap<>();
                    responseBody.put("message", "Login bem-sucedido");
                    responseBody.put("id", user.getId());

                    return ResponseEntity.ok(responseBody);
                })
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas")));
    }

}
