package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.ProfissaoDTO;
import com.unifor.indiestream.model.Profissao;
import com.unifor.indiestream.repository.ProfissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profissoes")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfissaoController {

    @Autowired
    private ProfissaoRepository profissaoRepository;

    @GetMapping
    public ResponseEntity<List<ProfissaoDTO>> getAllProfissoes() {
        List<ProfissaoDTO> profissoes = profissaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profissoes);
    }

    private ProfissaoDTO convertToDTO(Profissao profissao) {
        return ProfissaoDTO.builder()
                .id(profissao.getId())
                .nome(profissao.getNome())
                .build();
    }
}
