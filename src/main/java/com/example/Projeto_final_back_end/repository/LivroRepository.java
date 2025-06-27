package com.example.Projeto_final_back_end.repository;
import com.example.Projeto_final_back_end.entity.Livro;
import org.springframework.stereotype.Repository;

@Repository
public class LivroRepository extends FirebaseRepository<Livro> {
    public LivroRepository() {
        super("livros");
    }
}