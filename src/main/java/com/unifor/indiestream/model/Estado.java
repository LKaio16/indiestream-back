package com.unifor.indiestream.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "estado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private Set<Cidade> cidades;
}
