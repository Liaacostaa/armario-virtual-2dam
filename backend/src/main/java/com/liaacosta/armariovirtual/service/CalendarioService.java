package com.liaacosta.armariovirtual.service;

import com.liaacosta.armariovirtual.model.Calendario;
import com.liaacosta.armariovirtual.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioService {
    @Autowired
    private CalendarioRepository calendarioRepository;
    public Calendario guardarCalendario(Calendario calendario){
        return calendarioRepository.save(calendario);
    }
    public List<Calendario> listaUsuarios(Long id){
        return calendarioRepository.findByUsuarioId(id);
    }
    public void eliminarEvento(Long id){
        calendarioRepository.deleteById(id);
    }
}
