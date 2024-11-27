package com.unifor.indiestream.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComentarioDTO {
    private Long id;
    private String texto;
    private Long usuarioId;
    private String usuarioNome;
    private String usuarioImagemUrl; // Apenas os campos básicos
}
