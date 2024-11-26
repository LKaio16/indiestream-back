package com.unifor.indiestream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizarUsuarioDTO {
    private String nome;
    private String imagemUrl;
    private Long cidadeId;
    private Long estadoId;
    private Long profissaoId;
    private Set<Long> habilidades; // Lista de IDs das habilidades
    private String sobreMim;
    private Set<String> redesSociais; // Redes sociais como strings
}
