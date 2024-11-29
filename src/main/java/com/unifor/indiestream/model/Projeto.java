package com.unifor.indiestream.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "id_usuario_criador")
    private Usuario usuarioCriador; // Relacionamento com o criador do projeto

    @ManyToMany
    @JoinTable(
            name = "projeto_usuarios_solicitantes",
            joinColumns = @JoinColumn(name = "id_projeto"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private Set<Usuario> usuariosSolicitantes; // Lista de usuários solicitantes

    @ManyToMany
    @JoinTable(
            name = "projeto_pessoa_envolvida",
            joinColumns = @JoinColumn(name = "id_projeto"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private Set<Usuario> pessoasEnvolvidas;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LinhaDoTempo> linhaDoTempo;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios;
}
