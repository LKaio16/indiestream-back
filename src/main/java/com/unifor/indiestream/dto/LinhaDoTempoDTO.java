package com.unifor.indiestream.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinhaDoTempoDTO {
    private Long id;
    private String imagem;
    private String descricao;
}
