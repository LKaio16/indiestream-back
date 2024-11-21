package com.unifor.indiestream.service;

import com.unifor.indiestream.dto.ProjetoDTO;
import com.unifor.indiestream.dto.UsuarioDTO;
import com.unifor.indiestream.model.Usuario;
import com.unifor.indiestream.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            user.setProfissao(updatedUser.getProfissao());
            user.setHabilidades(updatedUser.getHabilidades());

            // Encripta a senha apenas se uma nova senha for fornecida
            if (updatedUser.getSenha() != null && !updatedUser.getSenha().isEmpty()) {
                user.setSenha(passwordEncoder.encode(updatedUser.getSenha()));
            }

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

    public List<UsuarioDTO> getAllUsersAsDTO() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUserByIdAsDTO(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    UsuarioDTO convertToDTO(Usuario user) {
        return UsuarioDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nome(user.getNome())
                .email(user.getEmail())
                .sobreMim(user.getSobreMin())
                .imagemUrl(user.getImagemUrl())
                .cidadeId(user.getCidade() != null ? user.getCidade().getId() : null)
                .cidadeNome(user.getCidade() != null ? user.getCidade().getNome() : null)
                .estadoId(user.getEstado() != null ? user.getEstado().getId() : null)
                .estadoNome(user.getEstado() != null ? user.getEstado().getNome() : null)
                .profissaoId(user.getProfissao() != null ? user.getProfissao().getId() : null)
                .profissaoNome(user.getProfissao() != null ? user.getProfissao().getNome() : null)
                .redesSociais(user.getRedesSociais())
                .habilidades(user.getHabilidades())
                .obrasFavoritas(user.getObrasFavoritas().stream()
                        .map(projeto -> ProjetoDTO.builder()
                                .id(projeto.getId())
                                .titulo(projeto.getTitulo())
                                .descricao(projeto.getDescricao())
                                .localizacao(projeto.getLocalizacao())
                                .imagemUrl(projeto.getImagemUrl())
                                .tipo(projeto.getTipo())
                                .status(projeto.getStatus())
                                .build())
                        .toList())
                .build();
    }


}