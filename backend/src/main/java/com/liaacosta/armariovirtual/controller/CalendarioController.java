package com.liaacosta.armariovirtual.controller;

import com.liaacosta.armariovirtual.model.Calendario;
import com.liaacosta.armariovirtual.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {
    @Autowired
    private CalendarioService calendarioService;

    @PostMapping("/crear")
    public Calendario crearCalendario(@RequestBody Calendario calendario){
        return calendarioService.guardarCalendario(calendario);
    }
    @GetMapping("/usuario/{id}")
    public List<Calendario> calendarioUsuario(@PathVariable Long id){
        return calendarioService.listaUsuarios(id);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarEvento(@PathVariable Long id){
        calendarioService.eliminarEvento(id);
    }
}
