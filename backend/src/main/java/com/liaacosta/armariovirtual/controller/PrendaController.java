package com.liaacosta.armariovirtual.controller;

import com.liaacosta.armariovirtual.model.*;
import com.liaacosta.armariovirtual.service.CloudinaryService;
import com.liaacosta.armariovirtual.service.PrendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/prendas")
public class PrendaController {
    @Autowired
    private PrendaService prendaService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/crear")
    public ResponseEntity<Prenda> crearPrenda(
            @RequestParam("nombre") String nombre,
            @RequestParam("tipo") String tipo,
            @RequestParam("color") String color,
            @RequestParam("ocasion") String ocasion,
            @RequestParam("temporada") String temporada,
            @RequestParam("usuarioId") Long usuarioId,
            @RequestParam(value = "foto", required = false) MultipartFile foto) throws IOException {

        Prenda prenda = new Prenda();
        prenda.setNombre(nombre);
        prenda.setTipo(TipoPrenda.valueOf(tipo));
        prenda.setColor(color);
        prenda.setOcasion(Ocasion.valueOf(ocasion));
        prenda.setTemporada(Temporada.valueOf(temporada));

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        prenda.setUsuario(usuario);

        if (foto != null && !foto.isEmpty()) {
            String urlFoto = cloudinaryService.subirImagen(foto);
            prenda.setFoto(urlFoto);
        }
        return ResponseEntity.ok(prendaService.guardarPrenda(prenda));
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
