package com.example.Projeto_final_back_end.service;

import com.example.Projeto_final_back_end.entity.BrasilApiLivroResponse;
import com.example.Projeto_final_back_end.entity.Livro;
import com.example.Projeto_final_back_end.exception.FirebaseOperationException;
import com.example.Projeto_final_back_end.exception.ResourceNotFoundException;
import com.example.Projeto_final_back_end.repository.LivroRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro getOne(String isbn) {
        try {
            Livro livro = livroRepository.findById(isbn, Livro.class);
            if (livro == null) {
                throw new ResourceNotFoundException("Livro com ISBN " + isbn + " não encontrado.");
            }
            return livro;
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar livro", e);
        }
    }

    public List<Livro> getAll() {
        try {
            return livroRepository.findAll(Livro.class);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar todos os livros", e);
        }
    }

    public void create(Livro livro) {
        try {
            String isbn = livro.getIsbn();
            String apiUrl = "https://brasilapi.com.br/api/isbn/v1/" + isbn;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() != 200) {
                throw new ResourceNotFoundException("ISBN não encontrado na BrasilAPI: " + isbn);
            }

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Gson gson = new Gson();
            BrasilApiLivroResponse apiLivro = gson.fromJson(reader, BrasilApiLivroResponse.class);

            System.out.println("Dados recebidos da API: " + apiLivro.getTitle());
            livro.setTitulo(apiLivro.getTitle());
            livro.setSubtitulo(apiLivro.getSubtitle());
            livro.setAutores(apiLivro.getAuthors());
            livro.setEditora(apiLivro.getPublisher());
            livro.setSinopse(apiLivro.getSynopsis());
            livro.setAno(apiLivro.getYear());
            livro.setPaginas(apiLivro.getPage_count());
            livro.setLocal(apiLivro.getLocation());
            livro.setFormato(apiLivro.getFormat());
            livro.setAssuntos(apiLivro.getSubjects());
            livro.setCapaUrl(apiLivro.getCover_url());

            String now = LocalDateTime.now().format(formatter);
            livro.setLastUpdated(now);

            System.out.println(now + " Livro salvo com sucesso no banco.");
            livroRepository.save(isbn, livro);
            System.out.println("Livro salvo com sucesso no banco.");

        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar ou salvar livro com ISBN", e);
        }
    }

    public void update(String isbn, Livro livroAtualizado) {
        try {
            getOne(isbn);
            String now = LocalDateTime.now().format(formatter);
            livroAtualizado.setLastUpdated(now);
            livroRepository.save(isbn, livroAtualizado);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao atualizar livro", e);
        }
    }

    public void delete(String isbn) {
        try {
            getOne(isbn);
            livroRepository.deleteById(isbn);
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao deletar livro", e);
        }
    }

    public List<Livro> getInativos() {
        try {
            LocalDateTime limite = LocalDateTime.now().minusWeeks(1);
            String limiteStr = limite.format(formatter);

            return livroRepository.findAll(Livro.class)
                    .stream()
                    .filter(l -> l.getLastUpdated() == null || l.getLastUpdated().compareTo(limiteStr) < 0)
                    .toList();
        } catch (Exception e) {
            throw new FirebaseOperationException("Erro ao buscar livros inativos", e);
        }
    }
}