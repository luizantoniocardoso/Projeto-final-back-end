package com.example.Projeto_final_back_end.controller;

import com.example.Projeto_final_back_end.entity.Usuario;
import com.example.Projeto_final_back_end.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public Usuario getOne(@PathVariable String id) {
        return usuarioService.getOne(id);
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

    @PostMapping
    public void create(@RequestBody Usuario usuario) {
        usuarioService.create(usuario);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody Usuario usuario) {
        usuarioService.update(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        usuarioService.delete(id);
    }

    @GetMapping("/inativos")
    public List<Usuario> getInativos() {
        return usuarioService.getInativos();
    }
}
