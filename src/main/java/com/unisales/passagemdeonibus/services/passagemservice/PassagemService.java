package com.unisales.passagemdeonibus.services.passagemservice;

import com.unisales.passagemdeonibus.domain.onibus.Onibus;
import com.unisales.passagemdeonibus.domain.onibus.OnibusRepository;
import com.unisales.passagemdeonibus.domain.passagem.Passagem;
import com.unisales.passagemdeonibus.domain.passagem.PassagemRepository;
import com.unisales.passagemdeonibus.domain.usuario.Usuario;
import com.unisales.passagemdeonibus.domain.usuario.UsuarioRepository;
import com.unisales.passagemdeonibus.exceptions.OnibusNotFoundException;
import com.unisales.passagemdeonibus.exceptions.PassagemAlreadyExistsException;
import com.unisales.passagemdeonibus.exceptions.PassagemNotFoundException;
import com.unisales.passagemdeonibus.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PassagemService {

    @Autowired
    private PassagemRepository passagemRepository;

    @Autowired
    private OnibusRepository onibusRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Passagem createPassagem(Passagem passagem) throws PassagemAlreadyExistsException, OnibusNotFoundException, UserNotFoundException {
        if (passagemRepository.findById(passagem.getId()).isPresent()) {
            throw new PassagemAlreadyExistsException("Essa passagem já existe no sistema");
        }

        Onibus onibus = onibusRepository.findByPlaca(passagem.getOnibus().getPlaca())
                .orElseThrow(() -> new OnibusNotFoundException("Ônibus não encontrado no nosso sistema"));

        Usuario usuario = usuarioRepository.findByEmail(passagem.getUsuario().getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado no nosso sistema"));

        passagem.setOnibus(onibus);
        passagem.setUsuario(usuario);

        return passagemRepository.save(passagem);

    }

    public Passagem getPassagem(String id) throws PassagemNotFoundException {
        return passagemRepository.findById(id)
                .orElseThrow(() -> new PassagemNotFoundException("Passagem não encontrada"));
    }

    public Passagem updatePassagem(Passagem updatePassagem) throws PassagemNotFoundException, OnibusNotFoundException, UserNotFoundException {
        Passagem passagem = passagemRepository.findById(updatePassagem.getId())
                .orElseThrow(() -> new PassagemNotFoundException("Passagem não encontrada"));

        Onibus onibus = onibusRepository.findByPlaca(updatePassagem.getOnibus().getPlaca())
                .orElseThrow(() -> new OnibusNotFoundException("Ônibus não encontrado no nosso sistema"));

        Usuario usuario = usuarioRepository.findByEmail(updatePassagem.getUsuario().getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado no nosso sistema"));

        passagem.setOnibus(onibus);
        passagem.setUsuario(usuario);
        passagem.setPreco(updatePassagem.getPreco());
        passagem.setDataViagem(updatePassagem.getDataViagem());
        passagem.setHoraViagem(updatePassagem.getHoraViagem());

        return passagemRepository.save(passagem);
    }

    public List<Passagem> getAllPassagens() {
        return passagemRepository.findAll();
    }

    public void deletePassagem(String id) throws PassagemNotFoundException {
        passagemRepository.findById(id)
                .orElseThrow(() -> new PassagemNotFoundException("Passagem não encontrada"));
        passagemRepository.deleteById(id);
    }
}
