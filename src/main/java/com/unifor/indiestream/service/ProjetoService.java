package com.unifor.indiestream.service;

import com.unifor.indiestream.dto.*;
import com.unifor.indiestream.model.Comentario;
import com.unifor.indiestream.model.LinhaDoTempo;
import com.unifor.indiestream.model.Projeto;
import com.unifor.indiestream.model.Usuario;
import com.unifor.indiestream.repository.ProjetoRepository;
import com.unifor.indiestream.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService; // Injetando UsuarioService

    public List<ProjetoDTO> getAllProjetos() {
        return projetoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProjetoDTO> getProjetoById(Long id) {
        return projetoRepository.findById(id)
                .map(this::convertToDTO);
    }

    public List<ProjetoDTO> getProjetosByUserId(Long userId) {
        return projetoRepository.findByPessoasEnvolvidasId(userId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ProjetoDTO createProjeto(Projeto projeto) {
        if (projeto.getPessoasEnvolvidas() != null && !projeto.getPessoasEnvolvidas().isEmpty()) {
            // Buscar os usuários pelo ID e adicionar ao projeto
            Set<Usuario> pessoasEnvolvidas = projeto.getPessoasEnvolvidas().stream()
                    .map(usuario -> usuarioRepository.findById(usuario.getId())
                            .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuario.getId())))
                    .collect(Collectors.toSet());
            projeto.setPessoasEnvolvidas(pessoasEnvolvidas);
        }
        Projeto savedProjeto = projetoRepository.save(projeto);
        return convertToDTO(savedProjeto);
    }


    public ProjetoDTO addPessoaEnvolvida(Long projetoId, Long usuarioId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado: " + projetoId));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));

        projeto.getPessoasEnvolvidas().add(usuario);
        Projeto updatedProjeto = projetoRepository.save(projeto);
        return convertToDTO(updatedProjeto);
    }

    public ProjetoDTO removePessoaEnvolvida(Long projetoId, Long usuarioId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado: " + projetoId));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));

        projeto.getPessoasEnvolvidas().remove(usuario);
        Projeto updatedProjeto = projetoRepository.save(projeto);
        return convertToDTO(updatedProjeto);
    }

    public void deleteProjeto(Long id) {
        if (!projetoRepository.existsById(id)) {
            throw new RuntimeException("Projeto não encontrado: " + id);
        }
        projetoRepository.deleteById(id);
    }

    public ProjetoDTO convertToDTO(Projeto projeto) {
        return ProjetoDTO.builder()
                .id(projeto.getId())
                .titulo(projeto.getTitulo())
                .descricao(projeto.getDescricao())
                .localizacao(projeto.getLocalizacao())
                .imagemUrl(projeto.getImagemUrl())
                .tipo(projeto.getTipo())
                .status(projeto.getStatus())
                .pessoasEnvolvidas(projeto.getPessoasEnvolvidas().stream()
                        .map(this::convertPessoaToDTO)
                        .toList())
                .linhaDoTempo(projeto.getLinhaDoTempo().stream()
                        .map(this::convertLinhaToDTO)
                        .toList())
                .comentarios(projeto.getComentarios().stream()
                        .map(this::convertComentarioToDTO)
                        .toList())
                .build();
    }

    private PessoaEnvolvidaDTO convertPessoaToDTO(Usuario usuario) {
        return PessoaEnvolvidaDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .imagemUrl(usuario.getImagemUrl())
                .cidadeNome(usuario.getCidade() != null ? usuario.getCidade().getNome() : null)
                .estadoNome(usuario.getEstado() != null ? usuario.getEstado().getNome() : null)
                .profissaoNome(usuario.getProfissao() != null ? usuario.getProfissao().getNome() : null)
                .build();
    }

    private ComentarioDTO convertComentarioToDTO(Comentario comentario) {
        return ComentarioDTO.builder()
                .id(comentario.getId())
                .texto(comentario.getTexto())
                .usuarioId(comentario.getUsuario().getId())
                .usuarioNome(comentario.getUsuario().getNome())
                .usuarioImagemUrl(comentario.getUsuario().getImagemUrl())
                .build();
    }

    private LinhaDoTempoDTO convertLinhaToDTO(LinhaDoTempo linhaDoTempo) {
        return LinhaDoTempoDTO.builder()
                .id(linhaDoTempo.getId())
                .imagem(linhaDoTempo.getImagem())
                .descricao(linhaDoTempo.getDescricao())
                .build();
    }
}
