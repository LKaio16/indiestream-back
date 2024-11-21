package com.unifor.indiestream.repository;

import com.unifor.indiestream.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
}
