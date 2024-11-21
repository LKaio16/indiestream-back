package com.unifor.indiestream.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto")
    private Long id;

    private String titulo;

    private String descricao;

    private String localizacao;

    @Column(name = "imagem_url")
    private String imagemUrl;

    private String tipo;

    private String status;

    @ManyToMany(mappedBy = "obrasFavoritas")
    private Set<Usuario> usuariosFavoritos;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private Set<LinhaDoTempo> linhaDoTempo;

    @ManyToMany
    @JoinTable(
            name = "projeto_pessoa_envolvida",
            joinColumns = @JoinColumn(name = "id_projeto"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private Set<Usuario> pessoasEnvolvidas;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios;
}
