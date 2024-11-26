package com.unifor.indiestream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoDTO {
    private Long id;
    private String nome;
    private List<CidadeDTO> cidades; // Lista de cidades simplificada
}
