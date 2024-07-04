package com.unisales.passagemdeonibus.controllers.onibuscontroller;


import com.unisales.passagemdeonibus.domain.onibus.Onibus;
import com.unisales.passagemdeonibus.exceptions.OnibusAlreadyExistsException;
import com.unisales.passagemdeonibus.exceptions.OnibusNotFoundException;
import com.unisales.passagemdeonibus.services.onibusservice.OnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/onibus")
public class OnibusController {
    @Autowired
    private OnibusService onibusService;

    @PostMapping
    public ResponseEntity<?> createOnibus(@RequestBody Onibus onibus) {
        try {
            Onibus newOnibus = onibusService.createOnibus(onibus);
            return ResponseEntity.ok(newOnibus);
        } catch (OnibusAlreadyExistsException e) {
            return ResponseEntity.status(400).body("O ônibus já existe no nosso sistema");
        }
    }

    @GetMapping("/{placa}")
    public ResponseEntity<?> getOnibus(@PathVariable String placa) {
        try {
            Onibus onibus = onibusService.getOnibus(placa);
            return ResponseEntity.ok(onibus);
        } catch (OnibusNotFoundException e) {
            return ResponseEntity.status(404).body("Ônibus não encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllOnibus() {
        List<Onibus> onibusList = onibusService.getAllOnibus();
        return ResponseEntity.ok(onibusList);
    }

    @PutMapping("/{placa}")
    public ResponseEntity<?> updateOnibus(@PathVariable String placa, @RequestBody Onibus updatedOnibus) {
        try {
            Onibus updated = onibusService.updateOnibus(updatedOnibus);
            return ResponseEntity.ok(updated);
        } catch (OnibusNotFoundException e) {
            return ResponseEntity.status(404).body("Ônibus não encontrado");
        }
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<?> deleteOnibus(@PathVariable String placa) {
        try {
            onibusService.deleteOnibus(placa);
            return ResponseEntity.ok("Ônibus deletado com sucesso");
        } catch (OnibusNotFoundException e) {
            return ResponseEntity.status(404).body("Ônibus não encontrado");
        }
    }
}
