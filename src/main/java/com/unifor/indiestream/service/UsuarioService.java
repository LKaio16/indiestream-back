package com.unifor.indiestream.service;

import com.unifor.indiestream.model.Usuario;
import com.unifor.indiestream.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    // Adicionando BCryptPasswordEncoder
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    public Usuario createUser(Usuario user) {
        // Encriptando a senha antes de salvar
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Usuario updateUser(Long id, Usuario updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setNome(updatedUser.getNome());
            // Encriptando a nova senha
            user.setSenha(passwordEncoder.encode(updatedUser.getSenha()));
            user.setEmail(updatedUser.getEmail());
            user.setImagemUrl(updatedUser.getImagemUrl());
            user.setDataNascimento(updatedUser.getDataNascimento());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Optional<Usuario> login(String email, String senha) {
        Optional<Usuario> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(senha, user.get().getSenha())) {
            return user;
        }
        return Optional.empty();
    }
}