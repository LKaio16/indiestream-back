package com.unifor.indiestream.repository;

import com.unifor.indiestream.model.Cidade;
import com.unifor.indiestream.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
