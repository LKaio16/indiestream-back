package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.ProjetoDTO;
import com.unifor.indiestream.model.Projeto;
import com.unifor.indiestream.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> getProjetos(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            // Chama o serviço para buscar os projetos do usuário específico
            return ResponseEntity.ok(projetoService.getProjetosByUserId(userId));
        }
        // Caso nenhum userId seja fornecido, retorna todos os projetos
        return ResponseEntity.ok(projetoService.getAllProjetos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> getProjetoById(@PathVariable Long id) {
        return projetoService.getProjetoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjetoDTO> createProjeto(@RequestBody Projeto projeto) {
        return ResponseEntity.ok(projetoService.createProjeto(projeto));
    }

    @PutMapping("/{id}/add-pessoa/{usuarioId}")
    public ResponseEntity<ProjetoDTO> addPessoaEnvolvida(@PathVariable Long id, @PathVariable Long usuarioId) {
        return ResponseEntity.ok(projetoService.addPessoaEnvolvida(id, usuarioId));
    }

    @DeleteMapping("/{id}/remove-pessoa/{usuarioId}")
    public ResponseEntity<ProjetoDTO> removePessoaEnvolvida(@PathVariable Long id, @PathVariable Long usuarioId) {
        return ResponseEntity.ok(projetoService.removePessoaEnvolvida(id, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id) {
        projetoService.deleteProjeto(id);
        return ResponseEntity.noContent().build();
    }
}
