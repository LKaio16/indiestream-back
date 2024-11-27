package com.unifor.indiestream.service;

import com.unifor.indiestream.dto.LinhaDoTempoDTO;
import com.unifor.indiestream.model.LinhaDoTempo;
import com.unifor.indiestream.repository.LinhaDoTempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinhaDoTempoService {

    @Autowired
    private LinhaDoTempoRepository linhaDoTempoRepository;

    public List<LinhaDoTempoDTO> getLinhaDoTempoByProjeto(Long projetoId) {
        List<LinhaDoTempo> linhaDoTempo = linhaDoTempoRepository.findByProjetoId(projetoId);

        return linhaDoTempo.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private LinhaDoTempoDTO convertToDTO(LinhaDoTempo linhaDoTempo) {
        return LinhaDoTempoDTO.builder()
                .id(linhaDoTempo.getId())
                .imagem(linhaDoTempo.getImagem())
                .descricao(linhaDoTempo.getDescricao())
                .build();
    }
}
