package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.CidadeDTO;
import com.unifor.indiestream.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cidades")
@CrossOrigin(origins = "http://localhost:3000")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public ResponseEntity<List<CidadeDTO>> getAllCidades() {
        List<CidadeDTO> cidades = cidadeRepository.findAll().stream()
                .map(cidade -> CidadeDTO.builder()
                        .id(cidade.getId())
                        .nome(cidade.getNome())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/by-estado/{estadoId}")
    public ResponseEntity<List<CidadeDTO>> getCidadesByEstado(@PathVariable Long estadoId) {
        List<CidadeDTO> cidades = cidadeRepository.findByEstadoId(estadoId).stream()
                .map(cidade -> CidadeDTO.builder()
                        .id(cidade.getId())
                        .nome(cidade.getNome())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(cidades);
    }
}
