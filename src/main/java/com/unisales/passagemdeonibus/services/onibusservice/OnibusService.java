package com.unisales.passagemdeonibus.services.onibusservice;

import com.unisales.passagemdeonibus.domain.onibus.Onibus;
import com.unisales.passagemdeonibus.domain.onibus.OnibusRepository;
import com.unisales.passagemdeonibus.exceptions.OnibusAlreadyExistsException;
import com.unisales.passagemdeonibus.exceptions.OnibusNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OnibusService {

    @Autowired
    private OnibusRepository onibusRepository;

    public Onibus createOnibus(Onibus onibus) throws OnibusAlreadyExistsException {
        Optional<Onibus> existingOnibus = onibusRepository.findByPlaca(onibus.getPlaca());
        if (existingOnibus.isPresent()) {
            throw new OnibusAlreadyExistsException("O ônibus já existe no nosso sistema.");
        }

        return onibusRepository.save(onibus);
    }

    public Onibus getOnibus(String placa) throws OnibusNotFoundException {
        return onibusRepository.findByPlaca(placa)
                .orElseThrow(() -> new OnibusNotFoundException("Ônibus não encontrado"));
    }

    public Onibus updateOnibus (Onibus updateOnibus) throws OnibusNotFoundException {
        return onibusRepository.findByPlaca(updateOnibus.getPlaca())
                .map(onibus -> {
                    onibus.setPlaca(updateOnibus.getPlaca());
                    onibus.setCapacidade(updateOnibus.getCapacidade());
                    onibus.setModelo((updateOnibus.getModelo()));
                    return onibusRepository.save(onibus);
                }).orElseThrow(() -> new OnibusNotFoundException("Ônibus não encontrado"));
    }

    public void deleteOnibus(String placa) throws OnibusNotFoundException {
        onibusRepository.findByPlaca(placa)
                .map(onibus -> {
                    onibusRepository.delete(onibus);
                    return null;
                }).orElseThrow(() -> new OnibusNotFoundException("Ônibus não encontrado"));
    }

    public List<Onibus> getAllOnibus() {
        return onibusRepository.findAll();
    }
}
