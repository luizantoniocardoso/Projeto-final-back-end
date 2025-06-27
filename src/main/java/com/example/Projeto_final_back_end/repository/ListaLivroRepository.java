package com.example.Projeto_final_back_end.repository;


import com.example.Projeto_final_back_end.entity.ListaLivro;
import org.springframework.stereotype.Repository;

@Repository

public class ListaLivroRepository extends FirebaseRepository<ListaLivro> {
    public ListaLivroRepository() {
        super("listaLivros");
    }
}