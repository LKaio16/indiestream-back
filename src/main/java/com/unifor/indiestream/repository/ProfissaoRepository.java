package com.unifor.indiestream.repository;

import com.unifor.indiestream.model.Habilidade;
import com.unifor.indiestream.model.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {
}
