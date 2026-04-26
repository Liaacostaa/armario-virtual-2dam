package com.liaacosta.armariovirtual.controller;

import com.liaacosta.armariovirtual.model.Outfit;
import com.liaacosta.armariovirtual.service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outfits")
public class OutfitController {
    @Autowired
    private OutfitService outfitService;

    @PostMapping("/crear")
    public Outfit crearOutfit(@RequestBody Outfit outfit){
        return outfitService.guardarOutfit(outfit);
    }
    @GetMapping("/usuario/{id}")
    public List<Outfit> outfitsUsuario(@PathVariable Long id){
        return outfitService.listaUsuarios(id);
    }
    @GetMapping("/favoritos/{id}")
    public List<Outfit> outfitsFavoritosUsuario(@PathVariable Long id){
        return outfitService.listarFavoritos(id);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarOutfit(@PathVariable Long id){
        outfitService.eliminarOutfit(id);
    }
}
