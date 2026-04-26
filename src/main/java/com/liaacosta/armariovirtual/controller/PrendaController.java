package com.liaacosta.armariovirtual.controller;

import com.liaacosta.armariovirtual.model.Prenda;
import com.liaacosta.armariovirtual.service.PrendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prendas")
public class PrendaController {
    @Autowired
    private PrendaService prendaService;

    @PostMapping("/crear")
    public Prenda crearPrenda(@RequestBody Prenda prenda){
        return prendaService.guardarPrenda(prenda);
    }
    @GetMapping("/usuario/{id}")
    public List<Prenda> prendasUsuario(@PathVariable Long id){
        return prendaService.listaPorUsuario(id);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarPrenda(@PathVariable Long id){
        prendaService.eliminarPrenda(id);
    }
}
