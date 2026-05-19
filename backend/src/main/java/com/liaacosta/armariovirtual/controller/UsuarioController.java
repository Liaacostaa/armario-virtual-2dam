package com.liaacosta.armariovirtual.controller;

import com.liaacosta.armariovirtual.config.JwtUtil;
import com.liaacosta.armariovirtual.model.LoginRequest;
import com.liaacosta.armariovirtual.model.LoginResponse;
import com.liaacosta.armariovirtual.model.Usuario;
import com.liaacosta.armariovirtual.service.CloudinaryService;
import com.liaacosta.armariovirtual.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private CloudinaryService cloudinaryService;

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
        return ResponseEntity.ok(new LoginResponse(token,usuario.get().getId()));
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        Usuario usuario = usuarioOpt.get();
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setEmail(usuarioActualizado.getEmail());
        return ResponseEntity.ok(usuarioService.guardarSinHash(usuario));
    }
    @PutMapping("/cambiar-contrasena/{id}")
    public ResponseEntity<?> cambiarContrasena(
            @PathVariable Long id,
            @RequestParam String contrasenaActual,
            @RequestParam String nuevaContrasena) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id);
        if (usuarioOpt.isEmpty()) return ResponseEntity.status(404).build();

        Usuario usuario = usuarioOpt.get();
        if (!bCryptPasswordEncoder.matches(contrasenaActual, usuario.getContrasena())) {
            return ResponseEntity.status(401).body("Contraseña actual incorrecta");
        }

        usuario.setContrasena(bCryptPasswordEncoder.encode(nuevaContrasena));
        usuarioService.guardarSinHash(usuario);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/foto-perfil/{id}")
    public ResponseEntity<Usuario> actualizarFotoPerfil(
            @PathVariable Long id,
            @RequestParam("foto") MultipartFile foto) throws IOException {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id);
        if (usuarioOpt.isEmpty()) return ResponseEntity.status(404).build();

        String urlFoto = cloudinaryService.subirImagen(foto);
        Usuario usuario = usuarioOpt.get();
        usuario.setFotoPerfil(urlFoto);
        return ResponseEntity.ok(usuarioService.guardarSinHash(usuario));
    }
}