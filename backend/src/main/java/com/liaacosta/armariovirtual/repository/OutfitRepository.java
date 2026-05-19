package com.liaacosta.armariovirtual.repository;

import com.liaacosta.armariovirtual.model.Outfit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutfitRepository extends JpaRepository<Outfit,Long> {
    List<Outfit> findByUsuarioId(Long id);
    List<Outfit> findByUsuarioIdAndFavorito(Long id, Boolean favorito);

}
