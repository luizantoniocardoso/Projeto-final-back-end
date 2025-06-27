package com.example.Projeto_final_back_end.service;

import com.example.Projeto_final_back_end.entity.Estante;
import com.example.Projeto_final_back_end.exception.FirebaseOperationException;
import com.example.Projeto_final_back_end.exception.ResourceNotFoundException;
import com.example.Projeto_final_back_end.repository.EstanteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class EstanteService {

    private final EstanteRepository estanteRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public EstanteService(EstanteRepository estanteRepository) {
        this.estanteRepository = estanteRepository;
    }

    public Estante getOne(String id) {
        try {
            Estante estante = estanteRepository.findById(id, Estante.class);
            if (estante == null) {
                throw new ResourceNotFoundException("Estante com id " + id + " não encontrada.");
            }
            return estante;
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar estante", e);
        }
    }

    public List<Estante> getAll() {
        try {
            return estanteRepository.findAll(Estante.class);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar todas as estantes", e);
        }
    }

    public void create(Estante estante) {
        try {
            String UUID_STRING = UUID.randomUUID().toString();
            String now = LocalDateTime.now().format(formatter);
            estante.setLastUpdated(now);
            estante.setId(UUID_STRING);
            estanteRepository.save(estante.getId(), estante);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao salvar estante", e);
        }
    }

    public void update(String id, Estante estanteAtualizada) {
        try {
            Estante existente = getOne(id);
            String now = LocalDateTime.now().format(formatter);

            // Atualiza os campos necessários
            estanteAtualizada.setId(id);
            estanteAtualizada.setLastUpdated(now);

            estanteRepository.save(id, estanteAtualizada);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao atualizar estante", e);
        }
    }

    public void delete(String id) {
        try {
            getOne(id); // Verifica se existe antes de deletar
            estanteRepository.deleteById(id);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao deletar estante", e);
        }
    }

    public List<Estante> getInativos() {
        try {
            LocalDateTime limite = LocalDateTime.now().minusWeeks(1);
            String limiteStr = limite.format(formatter);

            return estanteRepository.findAll(Estante.class)
                    .stream()
                    .filter(e -> e.getLastUpdated() == null ||
                            e.getLastUpdated().compareTo(limiteStr) < 0)
                    .toList();
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar estantes inativas", e);
        }
    }
}