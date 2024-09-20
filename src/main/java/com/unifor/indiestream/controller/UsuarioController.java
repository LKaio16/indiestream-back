package com.unifor.indiestream.controller;

import com.unifor.indiestream.model.Usuario;
import com.unifor.indiestream.repository.UsuarioRepository;
import com.unifor.indiestream.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
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

}
