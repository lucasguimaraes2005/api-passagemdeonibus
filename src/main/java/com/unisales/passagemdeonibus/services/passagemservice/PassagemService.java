package com.unisales.passagemdeonibus.services.passagemservice;

import com.unisales.passagemdeonibus.domain.onibus.Onibus;
import com.unisales.passagemdeonibus.domain.onibus.OnibusRepository;
import com.unisales.passagemdeonibus.domain.passagem.Passagem;
import com.unisales.passagemdeonibus.domain.passagem.PassagemRepository;
import com.unisales.passagemdeonibus.domain.passagemrequest.PassagemRequest;
import com.unisales.passagemdeonibus.domain.usuario.Usuario;
import com.unisales.passagemdeonibus.domain.usuario.UsuarioRepository;
import com.unisales.passagemdeonibus.exceptions.OnibusNotFoundException;
import com.unisales.passagemdeonibus.exceptions.PassagemAlreadyExistsException;
import com.unisales.passagemdeonibus.exceptions.PassagemNotFoundException;
import com.unisales.passagemdeonibus.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class PassagemService {

    @Autowired
    private PassagemRepository passagemRepository;

    @Autowired
    private OnibusRepository onibusRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Passagem createPassagem(PassagemRequest request) throws OnibusNotFoundException, UserNotFoundException {
        Onibus onibus = onibusRepository.findByPlaca(request.getOnibus())
                .orElseThrow(() -> new OnibusNotFoundException("Ônibus não encontrado no nosso sistema"));

        Usuario usuario = usuarioRepository.findByEmail(request.getUsuario())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado no nosso sistema"));

        Passagem passagem = new Passagem();
        passagem.setOnibus(onibus);
        passagem.setUsuario(usuario);
        passagem.setDataViagem(Date.valueOf(request.getDataViagem()));
        passagem.setHoraViagem(Time.valueOf(request.getHoraViagem()));
        passagem.setPreco(request.getPreco());

        return passagemRepository.save(passagem);
    }

    public Passagem getPassagem(String id) throws PassagemNotFoundException {
        return passagemRepository.findById(id)
                .orElseThrow(() -> new PassagemNotFoundException("Passagem não encontrada"));
    }

    public Passagem updatePassagem(String id, PassagemRequest updatePassagemRequest) throws PassagemNotFoundException, OnibusNotFoundException, UserNotFoundException {
        Passagem passagem = passagemRepository.findById(id)
                .orElseThrow(() -> new PassagemNotFoundException("Passagem não encontrada"));

        Onibus onibus = onibusRepository.findByPlaca(updatePassagemRequest.getOnibus())
                .orElseThrow(() -> new OnibusNotFoundException("Ônibus não encontrado no nosso sistema"));

        Usuario usuario = usuarioRepository.findByEmail(updatePassagemRequest.getUsuario())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado no nosso sistema"));

        passagem.setOnibus(onibus);
        passagem.setUsuario(usuario);
        passagem.setPreco(updatePassagemRequest.getPreco());

        // Convertendo data e hora do JSON para java.sql.Date e java.sql.Time
        java.sql.Date dataViagem = java.sql.Date.valueOf(updatePassagemRequest.getDataViagem());
        java.sql.Time horaViagem = java.sql.Time.valueOf(updatePassagemRequest.getHoraViagem());

        passagem.setDataViagem(dataViagem);
        passagem.setHoraViagem(horaViagem);

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
