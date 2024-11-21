package com.unifor.indiestream.controller;

import com.unifor.indiestream.model.Projeto;
import com.unifor.indiestream.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping
    public ResponseEntity<List<Projeto>> getAllProjetos() {
        return ResponseEntity.ok(projetoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto projeto) {
        return ResponseEntity.ok(projetoRepository.save(projeto));
    }
}
