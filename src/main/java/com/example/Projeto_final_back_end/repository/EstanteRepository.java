package com.example.Projeto_final_back_end.repository;

import com.example.Projeto_final_back_end.entity.Estante;
import org.springframework.stereotype.Repository;

@Repository

public class EstanteRepository extends FirebaseRepository<Estante>{
    public EstanteRepository() {
        super("estantes");
    }
}
