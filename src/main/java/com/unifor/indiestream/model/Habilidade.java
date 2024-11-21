package com.unifor.indiestream.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "habilidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habilidade")
    private Long id;

    private String nome;
}
