package com.unifor.indiestream.service;

import com.unifor.indiestream.dto.ComentarioDTO;
import com.unifor.indiestream.model.Comentario;
import com.unifor.indiestream.model.Projeto;
import com.unifor.indiestream.model.Usuario;
import com.unifor.indiestream.repository.ComentarioRepository;
import com.unifor.indiestream.repository.ProjetoRepository;
import com.unifor.indiestream.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<ComentarioDTO> getComentariosByProjetoId(Long projetoId) {
        return comentarioRepository.findByProjetoId(projetoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ComentarioDTO addComentario(Long projetoId, Long usuarioId, String texto) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado: " + projetoId));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));

        Comentario comentario = Comentario.builder()
                .texto(texto)
                .projeto(projeto)
                .usuario(usuario)
                .build();

        comentario = comentarioRepository.save(comentario);

        return convertToDTO(comentario);
    }

    private ComentarioDTO convertToDTO(Comentario comentario) {
        return ComentarioDTO.builder()
                .id(comentario.getId())
                .texto(comentario.getTexto())
                .usuarioId(comentario.getUsuario().getId())
                .usuarioNome(comentario.getUsuario().getNome())
                .usuarioImagemUrl(comentario.getUsuario().getImagemUrl())
                .build();
    }
}
