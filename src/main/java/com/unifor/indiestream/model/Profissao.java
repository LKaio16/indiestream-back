package com.unifor.indiestream.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profissao")
    private Long id;

    private String nome;
}
