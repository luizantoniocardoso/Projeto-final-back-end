package com.example.Projeto_final_back_end.controller;

import com.example.Projeto_final_back_end.entity.ListaLivro;
import com.example.Projeto_final_back_end.service.ListaLivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas-livro")
public class ListaLivroController {

    private final ListaLivroService listaLivroService;

    @Autowired
    public ListaLivroController(ListaLivroService listaLivroService) {
        this.listaLivroService = listaLivroService;
    }

    @GetMapping
    public ResponseEntity<List<ListaLivro>> getAllListas() {
        return ResponseEntity.ok(listaLivroService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaLivro> getListaById(@PathVariable String id) {
        return ResponseEntity.ok(listaLivroService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<ListaLivro> createLista(@RequestBody ListaLivro listaLivro) {
        listaLivroService.create(listaLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(listaLivro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaLivro> updateLista(
            @PathVariable String id,
            @RequestBody ListaLivro listaLivro) {
        listaLivroService.update(id, listaLivro);
        return ResponseEntity.ok(listaLivro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLista(@PathVariable String id) {
        listaLivroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inativas")
    public ResponseEntity<List<ListaLivro>> getListasInativas() {
        return ResponseEntity.ok(listaLivroService.getInativos());
    }

    @PostMapping("/{listaId}/livros/{livroId}")
    public ResponseEntity<Void> addLivroToLista(
            @PathVariable String listaId,
            @PathVariable String livroId) {
        return ResponseEntity.ok().build();
    }
}