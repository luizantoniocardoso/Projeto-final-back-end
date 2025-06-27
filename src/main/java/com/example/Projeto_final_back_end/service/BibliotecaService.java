package com.example.Projeto_final_back_end.service;

import com.example.Projeto_final_back_end.entity.Biblioteca;
import com.example.Projeto_final_back_end.exception.FirebaseOperationException;
import com.example.Projeto_final_back_end.exception.ResourceNotFoundException;
import com.example.Projeto_final_back_end.repository.BibliotecaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
    }

    public Biblioteca getOne(String id) {
        try {
            Biblioteca biblioteca = bibliotecaRepository.findById(id, Biblioteca.class);
            if (biblioteca == null) {
                throw new ResourceNotFoundException("Biblioteca com id " + id + " não encontrada.");
            }
            return biblioteca;
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar biblioteca", e);
        }
    }

    public List<Biblioteca> getAll() {
        try {
            return bibliotecaRepository.findAll(Biblioteca.class);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar todas as bibliotecas", e);
        }
    }

    public void create(Biblioteca biblioteca) {
        try {
            String now = LocalDateTime.now().format(formatter);
            biblioteca.setLastUpdated(now);
            bibliotecaRepository.save(biblioteca.getId(), biblioteca);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao salvar biblioteca", e);
        }
    }

    public void update(String id, Biblioteca bibliotecaAtualizada) {
        try {
            Biblioteca existente = getOne(id);
            String now = LocalDateTime.now().format(formatter);

            // Copia os campos necessários
            bibliotecaAtualizada.setId(id);
            bibliotecaAtualizada.setLastUpdated(now);

            bibliotecaRepository.save(id, bibliotecaAtualizada);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao atualizar biblioteca", e);
        }
    }

    public void delete(String id) {
        try {
            getOne(id);
            bibliotecaRepository.deleteById(id);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao deletar biblioteca", e);
        }
    }

    public List<Biblioteca> getInativos() {
        try {
            LocalDateTime limite = LocalDateTime.now().minusWeeks(1);
            String limiteStr = limite.format(formatter);

            return bibliotecaRepository.findAll(Biblioteca.class)
                    .stream()
                    .filter(b -> b.getLastUpdated() == null ||
                            b.getLastUpdated().compareTo(limiteStr) < 0)
                    .toList();
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar bibliotecas inativas", e);
        }
    }
}