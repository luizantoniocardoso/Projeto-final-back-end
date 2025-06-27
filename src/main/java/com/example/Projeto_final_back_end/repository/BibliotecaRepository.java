package com.example.Projeto_final_back_end.repository;

import com.example.Projeto_final_back_end.entity.Biblioteca;
import org.springframework.stereotype.Repository;

@Repository
public class BibliotecaRepository extends FirebaseRepository<Biblioteca> {
    public BibliotecaRepository() {
        super("bibliotecas");
    }
}