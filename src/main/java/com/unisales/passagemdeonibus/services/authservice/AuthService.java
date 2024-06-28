package com.unisales.passagemdeonibus.services.authservice;

import com.unisales.passagemdeonibus.domain.loginrequest.LoginRequest;
import com.unisales.passagemdeonibus.domain.usuario.Usuario;
import com.unisales.passagemdeonibus.domain.usuario.UsuarioRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Map<String, String> authenticate(LoginRequest loginRequest) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(loginRequest.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            if (passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())) {
                String token = Jwts.builder()
                        .setSubject(usuario.getId())
                        .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                        .signWith(SignatureAlgorithm.HS256, "5945a15423121ecc3542f9a7e30028dda8a17a8ab3cd8ef56ffa5f9cf43e3500")
                        .compact();
                Map<String, String> tokenResponse = new HashMap<>();
                tokenResponse.put("token", token);
                return tokenResponse;
            }
        }
        return null;
    }
}
