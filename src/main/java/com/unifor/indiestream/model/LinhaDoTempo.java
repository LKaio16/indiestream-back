package com.unifor.indiestream.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "linha_do_tempo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinhaDoTempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linha_tempo")
    private Long id;

    @Column(name = "imagem_url")
    private String imagem;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_projeto", nullable = false)
    @JsonBackReference // Evita que o Jackson serialize essa relação
    private Projeto projeto;

}
