package com.example.Projeto_final_back_end.controller;

import com.example.Projeto_final_back_end.entity.Livro;
import com.example.Projeto_final_back_end.service.LivroService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/{isbn}")
    public Livro getOne(@PathVariable String isbn) {
        return livroService.getOne(isbn);
    }

    @GetMapping
    public List<Livro> getAll() {
        return livroService.getAll();
    }

    @PostMapping
    public void create(@RequestBody Livro livro) {
        livroService.create(livro);
    }

    @PutMapping("/{isbn}")
    public void update(@PathVariable String isbn, @RequestBody Livro livro) {
        livroService.update(isbn, livro);
    }

    @DeleteMapping("/{isbn}")
    public void delete(@PathVariable String isbn) {
        livroService.delete(isbn);
    }

    @GetMapping("/inativos")
    public List<Livro> getInativos() {
        return livroService.getInativos();
    }
}
