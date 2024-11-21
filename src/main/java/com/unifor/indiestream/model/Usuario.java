package com.unifor.indiestream.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    private String username;

    private String nome;

    private String senha;

    private String email;

    @Column(name = "imagem_url")
    private String imagemUrl;

    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    @ManyToMany
    @JoinTable(
            name = "usuario_habilidade",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_habilidade")
    )
    private Set<Habilidade> habilidades;

    @ManyToOne
    @JoinColumn(name = "id_profissao")
    private Profissao profissao;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    @Column(name = "premium")
    private Boolean premium;

    @ElementCollection
    @CollectionTable(name = "usuario_rede_social", joinColumns = @JoinColumn(name = "id_usuario"))
    @Column(name = "rede_social")
    private Set<String> redesSociais;

    @Column(name = "sobre_min", columnDefinition = "TEXT")
    private String sobreMin;

    @ManyToMany
    @JoinTable(
            name = "obras_favoritas",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_projeto")
    )
    private Set<Projeto> obrasFavoritas;
}
