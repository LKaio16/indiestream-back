package com.unifor.indiestream.repository;

import com.unifor.indiestream.model.LinhaDoTempo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinhaDoTempoRepository extends JpaRepository<LinhaDoTempo, Long> {
    List<LinhaDoTempo> findByProjetoId(Long projetoId);
}
