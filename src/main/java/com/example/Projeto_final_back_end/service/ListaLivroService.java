package com.example.Projeto_final_back_end.service;

import com.example.Projeto_final_back_end.entity.ListaLivro;
import com.example.Projeto_final_back_end.exception.FirebaseOperationException;
import com.example.Projeto_final_back_end.exception.ResourceNotFoundException;
import com.example.Projeto_final_back_end.repository.ListaLivroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ListaLivroService {

    private final ListaLivroRepository listaLivroRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public ListaLivroService(ListaLivroRepository listaLivroRepository) {
        this.listaLivroRepository = listaLivroRepository;
    }

    public ListaLivro getOne(String id) {
        try {
            ListaLivro listaLivro = listaLivroRepository.findById(id, ListaLivro.class);
            if (listaLivro == null) {
                throw new ResourceNotFoundException("Lista de livros com id " + id + " não encontrada.");
            }
            return listaLivro;
        } catch (ExecutionException | InterruptedException e) {
            throw new FirebaseOperationException("Erro ao buscar lista de livros", e);
        }
    }

    public List<ListaLivro> getAll() {
        try {
            return listaLivroRepository.findAll(ListaLivro.class);
        } catch (ExecutionException | InterruptedException e) {
            throw new FirebaseOperationException("Erro ao buscar todas as listas de livro", e);
        }
    }

    public void create(ListaLivro listaLivro) {

            String now = LocalDateTime.now().format(formatter);
            listaLivro.setLastUpdated(now);
            listaLivroRepository.save(listaLivro.getId(), listaLivro);

    }

    public void update(String id, ListaLivro listaLivroAtualizada) {
        try {
            ListaLivro existente = getOne(id);
            String now = LocalDateTime.now().format(formatter);

            existente.setNome(listaLivroAtualizada.getNome());
            existente.setLivros(listaLivroAtualizada.getLivros());
            existente.setLastUpdated(now);

            listaLivroRepository.save(id, existente);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao atualizar lista de livros", e);
        }
    }

    public void delete(String id) {
        try {
            getOne(id); // Verifica se existe antes de deletar
            listaLivroRepository.deleteById(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new FirebaseOperationException("Erro ao deletar lista de livros", e);
        }
    }

    public List<ListaLivro> getInativos() {
        try {
            List<ListaLivro> todasListas = listaLivroRepository.findAll(ListaLivro.class);
            LocalDateTime umaSemanaAtras = LocalDateTime.now().minusWeeks(1);
            String umaSemanaAtrasStr = umaSemanaAtras.format(formatter);

            return todasListas.stream()
                    .filter(lista -> lista.getLastUpdated() == null ||
                            lista.getLastUpdated().compareTo(umaSemanaAtrasStr) < 0)
                    .collect(Collectors.toList());
        } catch (ExecutionException | InterruptedException e) {
            throw new FirebaseOperationException("Erro ao buscar listas de livro inativas", e);
        }
    }
}