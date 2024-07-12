package com.alura.forohub.repository;

import com.alura.forohub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByCursoNombre(String cursoNombre, Pageable pageable);
    Page<Topico> findByAnio(Integer anio, Pageable pageable);
    Page<Topico> findByCursoNombreAndAnio(String cursoNombre, Integer anio, Pageable pageable);
}
