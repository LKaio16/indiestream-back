package com.unifor.indiestream.dto;

import com.unifor.indiestream.model.Habilidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String username;
    private String nome;
    private String email;
    private String sobreMim;
    private String imagemUrl;
    private Long cidadeId; // ID da cidade
    private String cidadeNome; // Nome da cidade
    private Long estadoId; // ID do estado
    private String estadoNome; // Nome do estado
    private Long profissaoId; // ID da profissão
    private String profissaoNome;
    private Set<String> redesSociais;
    private Set<Habilidade> habilidades;
    private List<ProjetoDTO> obrasFavoritas;
}
