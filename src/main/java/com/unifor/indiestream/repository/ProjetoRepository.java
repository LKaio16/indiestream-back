package com.unifor.indiestream.repository;

import com.unifor.indiestream.model.Cidade;
import com.unifor.indiestream.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByPessoasEnvolvidasId(Long usuarioId);
}

