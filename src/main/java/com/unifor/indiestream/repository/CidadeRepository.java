package com.unifor.indiestream.repository;

import com.unifor.indiestream.model.Cidade;
import com.unifor.indiestream.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByEstadoId(Long estadoId);
}
