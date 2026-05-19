package com.liaacosta.armariovirtual.repository;

import com.liaacosta.armariovirtual.model.Calendario;
import com.liaacosta.armariovirtual.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarioRepository extends JpaRepository<Calendario,Long> {
    List<Calendario> findByUsuarioId(Long id);
}
