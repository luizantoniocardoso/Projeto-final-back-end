package com.example.Projeto_final_back_end.controller;

import com.example.Projeto_final_back_end.entity.Estante;
import com.example.Projeto_final_back_end.service.EstanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estantes")
public class EstanteController {

    private final EstanteService estanteService;

    @Autowired
    public EstanteController(EstanteService estanteService) {
        this.estanteService = estanteService;
    }

    @GetMapping
    public ResponseEntity<List<Estante>> getAllEstantes() {
        return ResponseEntity.ok(estanteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estante> getEstanteById(@PathVariable String id) {
        return ResponseEntity.ok(estanteService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Estante> createEstante( @RequestBody Estante estante) {
        estanteService.create(estante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estante> updateEstante(
            @PathVariable String id,
            @RequestBody Estante estante) {
        estanteService.update(id, estante);
        return ResponseEntity.ok(estante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstante(@PathVariable String id) {
        estanteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inativas")
    public ResponseEntity<List<Estante>> getEstantesInativas() {
        return ResponseEntity.ok(estanteService.getInativos());
    }
}