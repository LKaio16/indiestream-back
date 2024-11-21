package com.unifor.indiestream.dto;

import com.unifor.indiestream.model.Comentario;
import com.unifor.indiestream.model.LinhaDoTempo;
import com.unifor.indiestream.model.Usuario;
import com.unifor.indiestream.repository.LinhaDoTempoRepository;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String localizacao;
    private String imagemUrl;
    private String tipo;
    private String status;
    private List<UsuarioDTO> pessoasEnvolvidas;
    private List<LinhaDoTempo> linhaDoTempo;
    private List<Comentario> comentarios;
}
