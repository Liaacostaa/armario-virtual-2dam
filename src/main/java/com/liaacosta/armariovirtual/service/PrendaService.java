package com.liaacosta.armariovirtual.service;

import com.liaacosta.armariovirtual.model.Prenda;
import com.liaacosta.armariovirtual.repository.PrendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrendaService {
    @Autowired
    private PrendaRepository prendaRepository;

    public Prenda guardarPrenda(Prenda prenda){
        return prendaRepository.save(prenda);
    }
    public List<Prenda> listaPorUsuario(Long id_usuario){
        return prendaRepository.findByUsuarioId(id_usuario);
    }
    public void eliminarPrenda(Long id){
        prendaRepository.deleteById(id);
    }
}
