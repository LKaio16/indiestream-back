package com.unifor.indiestream.controller;

import com.unifor.indiestream.dto.ComentarioDTO;
import com.unifor.indiestream.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "http://localhost:3000")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/projeto/{projetoId}")
    public List<ComentarioDTO> getComentariosByProjeto(@PathVariable Long projetoId) {
        return comentarioService.getComentariosByProjetoId(projetoId);
    }

    @PostMapping("/projeto/{projetoId}/usuario/{usuarioId}")
    public ComentarioDTO addComentario(
            @PathVariable Long projetoId,
            @PathVariable Long usuarioId,
            @RequestBody Map<String, String> payload
    ) {
        String texto = payload.get("texto");
        return comentarioService.addComentario(projetoId, usuarioId, texto);
    }
}
