package com.example.Projeto_final_back_end.service;

import com.example.Projeto_final_back_end.entity.Usuario;
import com.example.Projeto_final_back_end.exception.FirebaseOperationException;
import com.example.Projeto_final_back_end.exception.ResourceNotFoundException;
import com.example.Projeto_final_back_end.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getOne(String id) {
        try {
            Usuario usuario = usuarioRepository.findById(id, Usuario.class);
            if (usuario == null) {
                throw new ResourceNotFoundException("Usuário com id " + id + " não encontrado.");
            }
            return usuario;
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar usuário", e);
        }
    }

    public List<Usuario> getAll() {
        try {
            return usuarioRepository.findAll(Usuario.class);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar todos os usuários", e);
        }
    }

    public void create(Usuario usuario) {
        try {
            String UUID_STRING = UUID.randomUUID().toString();
            String now = LocalDateTime.now().format(formatter);
            usuario.setLastUpdated(now);
            usuario.setId(UUID_STRING);
            usuarioRepository.save(UUID_STRING, usuario);

        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao salvar usuário", e);
        }
    }

    public void update(String id, Usuario usuarioAtualizado) {
        try {
            Usuario existente = getOne(id);
            String now = LocalDateTime.now().format(formatter);
            usuarioAtualizado.setLastUpdated(now);
            usuarioRepository.save(id, usuarioAtualizado);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao atualizar usuário", e);
        }
    }

    public void delete(String id) {
        try {
            getOne(id);
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao deletar usuário", e);
        }
    }

    public List<Usuario> getInativos() {
        try {
            LocalDateTime limite = LocalDateTime.now().minusWeeks(1);
            String limiteStr = limite.format(formatter);

            return usuarioRepository.findAll(Usuario.class)
                    .stream()
                    .filter(u -> u.getLastUpdated() == null || u.getLastUpdated().compareTo(limiteStr) < 0)
                    .toList();
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar usuários inativos", e);
        }
    }
}