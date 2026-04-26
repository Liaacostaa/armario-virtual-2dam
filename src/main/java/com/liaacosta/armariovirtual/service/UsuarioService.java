package com.liaacosta.armariovirtual.service;

import com.liaacosta.armariovirtual.model.Usuario;
import com.liaacosta.armariovirtual.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Usuario guardar(Usuario usuario){
        String contrasenaHasheada = bCryptPasswordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(contrasenaHasheada);
        return usuarioRepository.save(usuario);
    }
    public Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}
