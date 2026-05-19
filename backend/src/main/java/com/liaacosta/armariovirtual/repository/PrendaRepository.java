package com.liaacosta.armariovirtual.repository;

import com.liaacosta.armariovirtual.model.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PrendaRepository extends JpaRepository<Prenda,Long> {
    List<Prenda> findByUsuarioId(Long id);
}
