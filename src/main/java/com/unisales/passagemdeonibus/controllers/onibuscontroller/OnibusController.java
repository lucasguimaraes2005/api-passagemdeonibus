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
    public ResponseEntity<Onibus> createOnibus(@RequestBody Onibus onibus) throws OnibusAlreadyExistsException {
        return new ResponseEntity<>(onibusService.createOnibus(onibus), HttpStatus.CREATED);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Onibus> getOnibus(@PathVariable String placa) throws OnibusNotFoundException {
        return new ResponseEntity<>(onibusService.getOnibus(placa), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Onibus>> getAllOnibus() {
        return new ResponseEntity<>(onibusService.getAllOnibus(), HttpStatus.OK);
    }

    @PutMapping("/{placa}")
    public ResponseEntity<Onibus> updateOnibus(@PathVariable String placa, @RequestBody Onibus updatedOnibus) throws OnibusNotFoundException {
        return new ResponseEntity<>(onibusService.updateOnibus(updatedOnibus), HttpStatus.OK);
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> deleteOnibus(@PathVariable String placa) throws OnibusNotFoundException {
        onibusService.deleteOnibus(placa);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
