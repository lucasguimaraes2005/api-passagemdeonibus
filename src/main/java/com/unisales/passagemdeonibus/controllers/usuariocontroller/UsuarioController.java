package com.unisales.passagemdeonibus.controllers.usuariocontroller;


import com.unisales.passagemdeonibus.domain.usuario.Usuario;
import com.unisales.passagemdeonibus.exceptions.UserAlreadyExistsException;
import com.unisales.passagemdeonibus.exceptions.UserNotFoundException;
import com.unisales.passagemdeonibus.services.usuarioservice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{email}")
    public ResponseEntity<?> getUsuario(@PathVariable String email) {
        try {
            Usuario usuario = usuarioService.getUsuario(email);
            return ResponseEntity.ok(usuario);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateUsuario(@PathVariable String email, @RequestBody Usuario updatedUsuario) {
        try {
            Usuario updated = usuarioService.updateUsuario(updatedUsuario);
            return ResponseEntity.ok(updated);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUsuario(@PathVariable String email) {
        try {
            usuarioService.deleteUsuario(email);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }
}
