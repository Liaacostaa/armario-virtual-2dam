package com.liaacosta.armariovirtual.controller;

import com.liaacosta.armariovirtual.config.JwtUtil;
import com.liaacosta.armariovirtual.model.LoginRequest;
import com.liaacosta.armariovirtual.model.LoginResponse;
import com.liaacosta.armariovirtual.model.Usuario;
import com.liaacosta.armariovirtual.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/lista")
    public List<Usuario> getUsuarios() {
        return usuarioService.listarTodos();
    }
    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.guardar(usuario);
    }
    @GetMapping("/buscar")
    public Optional<Usuario> buscarPorEmail(@RequestParam String email){
        return usuarioService.buscarPorEmail(email);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> iniciarSesion(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioService.buscarPorEmail(loginRequest.getEmail());
        if (usuario.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        boolean contrasenaCorrecta = bCryptPasswordEncoder.matches(
                loginRequest.getContrasena(),
                usuario.get().getContrasena()
        );
        if (!contrasenaCorrecta) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtUtil.generarToken(usuario.get().getEmail());
        return ResponseEntity.ok(new LoginResponse(token));
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}