package com.unifor.indiestream.repository;

import com.unifor.indiestream.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    @Query("SELECT c FROM Comentario c JOIN FETCH c.usuario u WHERE c.projeto.id = :projetoId")
    List<Comentario> findByProjetoId(@Param("projetoId") Long projetoId);
}
