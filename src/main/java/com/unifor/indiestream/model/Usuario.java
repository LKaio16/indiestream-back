package com.unifor.indiestream.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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


}
