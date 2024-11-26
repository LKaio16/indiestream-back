package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.HabilidadeDTO;
import com.unifor.indiestream.model.Habilidade;
import com.unifor.indiestream.repository.HabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/habilidades")
@CrossOrigin(origins = "http://localhost:3000")
public class HabilidadeController {

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @GetMapping
    public ResponseEntity<List<HabilidadeDTO>> getAllHabilidades() {
        List<HabilidadeDTO> habilidades = habilidadeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(habilidades);
    }

    private HabilidadeDTO convertToDTO(Habilidade habilidade) {
        return HabilidadeDTO.builder()
                .id(habilidade.getId())
                .nome(habilidade.getNome())
                .build();
    }
}
