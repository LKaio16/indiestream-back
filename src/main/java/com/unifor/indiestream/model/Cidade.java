package com.unifor.indiestream.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    @JsonBackReference // Evitar o loop serializando apenas o Estado
    private Estado estado;

}
