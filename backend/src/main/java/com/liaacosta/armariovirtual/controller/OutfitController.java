package com.liaacosta.armariovirtual.controller;

import com.liaacosta.armariovirtual.model.Ocasion;
import com.liaacosta.armariovirtual.model.Outfit;
import com.liaacosta.armariovirtual.model.Prenda;
import com.liaacosta.armariovirtual.model.Usuario;
import com.liaacosta.armariovirtual.service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outfits")
public class OutfitController {
    @Autowired
    private OutfitService outfitService;

    @PostMapping("/crear")
    public ResponseEntity<Outfit> crearOutfit(
            @RequestParam String nombre,
            @RequestParam String ocasion,
            @RequestParam Long usuarioId,
            @RequestParam List<Long> prendasIds) {

        Outfit outfit = new Outfit();
        outfit.setNombre(nombre);
        outfit.setOcasion(Ocasion.valueOf(ocasion));
        outfit.setFavorito(false);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        outfit.setUsuario(usuario);

        List<Prenda> prendas = prendasIds.stream()
                .map(id -> {
                    Prenda p = new Prenda();
                    p.setId(id);
                    return p;
                })
                .collect(java.util.stream.Collectors.toList());
        outfit.setPrendas(prendas);

        return ResponseEntity.ok(outfitService.guardarOutfit(outfit));
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
    @PutMapping("/favorito/{id}")
    public ResponseEntity<Outfit> toggleFavorito(@PathVariable Long id) {
        Outfit outfit = outfitService.buscarPorId(id);
        outfit.setFavorito(!outfit.getFavorito());
        return ResponseEntity.ok(outfitService.guardarOutfit(outfit));
    }
}
