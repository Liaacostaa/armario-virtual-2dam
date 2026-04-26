package com.liaacosta.armariovirtual.service;

import com.liaacosta.armariovirtual.model.Outfit;
import com.liaacosta.armariovirtual.repository.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OutfitService {
    @Autowired
    private OutfitRepository outfitRepository;
    public Outfit guardarOutfit(Outfit outfit){
        return outfitRepository.save(outfit);
    }
    public List<Outfit> listaUsuarios(Long id){
        return outfitRepository.findByUsuarioId(id);
    }
    public List<Outfit> listarFavoritos(Long id) {
        return outfitRepository.findByUsuarioIdAndFavorito(id,true);
    }
    public void eliminarOutfit(Long id){
        outfitRepository.deleteById(id);
    }
}
