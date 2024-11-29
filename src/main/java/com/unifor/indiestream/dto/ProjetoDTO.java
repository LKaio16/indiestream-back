package com.unifor.indiestream.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjetoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String localizacao;
    private String imagemUrl;
    private String tipo;
    private String status;
    private PessoaEnvolvidaDTO usuarioCriador;
    private List<PessoaEnvolvidaDTO> usuariosSolicitantes;
    private List<PessoaEnvolvidaDTO> pessoasEnvolvidas;
    private List<LinhaDoTempoDTO> linhaDoTempo;
    private List<ComentarioDTO> comentarios;
}

