package com.example.Projeto_final_back_end.controller;

import com.example.Projeto_final_back_end.entity.Biblioteca;
import com.example.Projeto_final_back_end.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    @Autowired
    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @GetMapping
    public ResponseEntity<List<Biblioteca>> getAllBibliotecas() {
        return ResponseEntity.ok(bibliotecaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> getBibliotecaById(@PathVariable String id) {
        return ResponseEntity.ok(bibliotecaService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Biblioteca> createBiblioteca(
            @RequestBody Biblioteca biblioteca) {
        bibliotecaService.create(biblioteca);
        return ResponseEntity.status(HttpStatus.CREATED).body(biblioteca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> updateBiblioteca(
            @PathVariable String id,
            @RequestBody Biblioteca biblioteca) {
        bibliotecaService.update(id, biblioteca);
        return ResponseEntity.ok(biblioteca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBiblioteca(@PathVariable String id) {
        bibliotecaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inativas")
    public ResponseEntity<List<Biblioteca>> getBibliotecasInativas() {
        return ResponseEntity.ok(bibliotecaService.getInativos());
    }

    @PostMapping("/{bibliotecaId}/estantes/{estanteId}")
    public ResponseEntity<Void> addEstanteToBiblioteca(
            @PathVariable String bibliotecaId,
            @PathVariable String estanteId) {
        return ResponseEntity.ok().build();
    }
}