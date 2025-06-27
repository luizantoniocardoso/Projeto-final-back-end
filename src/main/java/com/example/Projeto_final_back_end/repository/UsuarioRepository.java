package com.example.Projeto_final_back_end.repository;

import com.example.Projeto_final_back_end.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository extends FirebaseRepository<Usuario> {
    public UsuarioRepository() {
        super("usuarios");
    }
}