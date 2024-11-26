package com.unifor.indiestream.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore // Ignorar a serialização de cidades no Estado
    private Set<Cidade> cidades;

}
