package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.LinhaDoTempoDTO;
import com.unifor.indiestream.service.LinhaDoTempoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/linhaDoTempo")
@CrossOrigin(origins = "http://localhost:3000")
public class LinhaDoTempoController {

    @Autowired
    private LinhaDoTempoService linhaDoTempoService;

    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<List<LinhaDoTempoDTO>> getLinhaDoTempoByProjeto(@PathVariable Long projetoId) {
        List<LinhaDoTempoDTO> linhaDoTempo = linhaDoTempoService.getLinhaDoTempoByProjeto(projetoId);
        if (linhaDoTempo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(linhaDoTempo);
    }
}
