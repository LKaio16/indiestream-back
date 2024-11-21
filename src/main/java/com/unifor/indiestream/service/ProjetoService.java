package com.unifor.indiestream.service;

import com.unifor.indiestream.dto.ProjetoDTO;
import com.unifor.indiestream.dto.UsuarioDTO;
import com.unifor.indiestream.model.Projeto;
import com.unifor.indiestream.model.Usuario;
import com.unifor.indiestream.repository.ProjetoRepository;
import com.unifor.indiestream.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public ProjetoDTO createProjeto(Projeto projeto) {
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

    public void deleteProjeto(Long id) {
        if (!projetoRepository.existsById(id)) {
            throw new RuntimeException("Projeto não encontrado: " + id);
        }
        projetoRepository.deleteById(id);
    }

    private ProjetoDTO convertToDTO(Projeto projeto) {
        List<UsuarioDTO> pessoasEnvolvidas = projeto.getPessoasEnvolvidas().stream()
                .map(usuarioService::convertToDTO) // Usando o método do UsuarioService
                .collect(Collectors.toList());

        return ProjetoDTO.builder()
                .id(projeto.getId())
                .titulo(projeto.getTitulo())
                .descricao(projeto.getDescricao())
                .localizacao(projeto.getLocalizacao())
                .imagemUrl(projeto.getImagemUrl())
                .tipo(projeto.getTipo())
                .status(projeto.getStatus())
                .pessoasEnvolvidas(pessoasEnvolvidas)
                .linhaDoTempo(projeto.getLinhaDoTempo() != null ? List.copyOf(projeto.getLinhaDoTempo()) : null)
                .comentarios(projeto.getComentarios() != null ? List.copyOf(projeto.getComentarios()) : null)
                .build();
    }
}
