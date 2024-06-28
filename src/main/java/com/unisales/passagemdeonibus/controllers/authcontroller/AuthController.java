package com.unisales.passagemdeonibus.controllers.authcontroller;

import com.unisales.passagemdeonibus.domain.loginrequest.LoginRequest;
import com.unisales.passagemdeonibus.domain.usuario.Usuario;
import com.unisales.passagemdeonibus.exceptions.UserAlreadyExistsException;
import com.unisales.passagemdeonibus.services.authservice.AuthService;
import com.unisales.passagemdeonibus.services.usuarioservice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Map<String, String> tokenResponse = authService.authenticate(loginRequest);
        if (tokenResponse != null) {
            return ResponseEntity.ok(tokenResponse);
        } else {
            return ResponseEntity.status(401).body("Usuário não encontrado ou senha incorreta");
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> createUser(@RequestBody Usuario user) {
        try {
            Usuario newUser = userService.createUsuario(user);
            return ResponseEntity.ok(newUser);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(400).body("Usuário já existe no nosso sistema");
        }
    }
}
