package com.unisales.passagemdeonibus.controllers.passagemcontroller;


import com.unisales.passagemdeonibus.domain.passagem.Passagem;
import com.unisales.passagemdeonibus.domain.passagemrequest.PassagemRequest;
import com.unisales.passagemdeonibus.exceptions.OnibusNotFoundException;
import com.unisales.passagemdeonibus.exceptions.PassagemAlreadyExistsException;
import com.unisales.passagemdeonibus.exceptions.PassagemNotFoundException;
import com.unisales.passagemdeonibus.exceptions.UserNotFoundException;
import com.unisales.passagemdeonibus.services.passagemservice.PassagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passagens")
public class PassagemController {

    @Autowired
    private PassagemService passagemService;

    @PostMapping
    public ResponseEntity<?> createPassagem(@RequestBody PassagemRequest request) {
        try {
            Passagem newPassagem = passagemService.createPassagem(request);
            return ResponseEntity.ok(newPassagem);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        } catch (OnibusNotFoundException e) {
            return ResponseEntity.status(404).body("Ônibus não encontrado");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPassagem(@PathVariable String id) {
        try {
            Passagem passagem = passagemService.getPassagem(id);
            return ResponseEntity.ok(passagem);
        } catch (PassagemNotFoundException e) {
            return ResponseEntity.status(404).body("Passagem não encontrada");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPassagens() {
        List<Passagem> passagens = passagemService.getAllPassagens();
        return ResponseEntity.ok(passagens);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePassagem(@PathVariable String id, @RequestBody PassagemRequest updatedPassagem) {
        try {
            Passagem updated = passagemService.updatePassagem(id, updatedPassagem);
            return ResponseEntity.ok(updated);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        } catch (PassagemNotFoundException e) {
            return ResponseEntity.status(404).body("Passagem não encontrada");
        } catch (OnibusNotFoundException e) {
            return ResponseEntity.status(404).body("Ônibus não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassagem(@PathVariable String id) {
        try {
            passagemService.deletePassagem(id);
            return ResponseEntity.ok("Passagem deletada com sucesso");
        } catch (PassagemNotFoundException e) {
            return ResponseEntity.status(404).body("Passagem não encontrada");
        }
    }
}
