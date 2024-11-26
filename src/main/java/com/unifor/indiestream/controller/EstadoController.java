package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.CidadeDTO;
import com.unifor.indiestream.dto.EstadoDTO;
import com.unifor.indiestream.model.Estado;
import com.unifor.indiestream.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estados")
@CrossOrigin(origins = "http://localhost:3000")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> getAllEstados() {
        List<EstadoDTO> estados = estadoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(estados);
    }

    private EstadoDTO convertToDTO(Estado estado) {
        List<CidadeDTO> cidades = estado.getCidades().stream()
                .map(cidade -> CidadeDTO.builder()
                        .id(cidade.getId())
                        .nome(cidade.getNome())
                        .build())
                .collect(Collectors.toList());

        return EstadoDTO.builder()
                .id(estado.getId())
                .nome(estado.getNome())
                .cidades(cidades)
                .build();
    }
}
