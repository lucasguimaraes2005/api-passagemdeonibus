package com.unisales.passagemdeonibus.services.usuarioservice;

import com.unisales.passagemdeonibus.domain.passagem.Passagem;
import com.unisales.passagemdeonibus.domain.usuario.Usuario;
import com.unisales.passagemdeonibus.domain.usuario.UsuarioRepository;
import com.unisales.passagemdeonibus.exceptions.UserAlreadyExistsException;
import com.unisales.passagemdeonibus.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUsuario(Usuario usuario) throws UserAlreadyExistsException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(hashedPassword);

        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUsuario.isPresent()) {
            throw new UserAlreadyExistsException("Usuário já existe no sistema");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuario(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public Usuario updateUsuario(Usuario updatedUsuario) throws UserNotFoundException {
        return usuarioRepository.findByEmail(updatedUsuario.getEmail())
                .map(usuario -> {
                    usuario.setNome(updatedUsuario.getNome());
                    usuario.setEmail(updatedUsuario.getEmail());
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String hashedPassword = passwordEncoder.encode(updatedUsuario.getSenha());
                    usuario.setSenha(hashedPassword);
                    return usuarioRepository.save(usuario);
                }).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    public void deleteUsuario(String email) throws UserNotFoundException {
        usuarioRepository.findByEmail(email)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return null;
                }).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
