package com.unisales.passagemdeonibus.controllers.passagemcontroller;


import com.unisales.passagemdeonibus.domain.passagem.Passagem;
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
    public ResponseEntity<Passagem> createPassagem(@RequestBody Passagem passagem) throws UserNotFoundException, PassagemAlreadyExistsException, OnibusNotFoundException {
        return new ResponseEntity<>(passagemService.createPassagem(passagem), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passagem> getPassagem(@PathVariable String id) throws PassagemNotFoundException {
        return new ResponseEntity<>(passagemService.getPassagem(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Passagem>> getAllPassagens() {
        return new ResponseEntity<>(passagemService.getAllPassagens(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passagem> updatePassagem(@PathVariable Long id, @RequestBody Passagem updatedPassagem) throws UserNotFoundException, PassagemNotFoundException, OnibusNotFoundException {
        return new ResponseEntity<>(passagemService.updatePassagem(updatedPassagem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassagem(@PathVariable String id) throws PassagemNotFoundException {
        passagemService.deletePassagem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
