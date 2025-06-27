package com.example.Projeto_final_back_end.entity;

import java.time.LocalDateTime;
import java.util.List;

public class ListaLivro {
    private String id; // ID único da lista
    private String idLeitor; // Dono da lista
    private String nome; // Nome personalizado da lista
    private boolean publica; // Pode ser vista por outros usuários
    private List<LivroLista> livros; // Livros nessa lista
    private String lastUpdated;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLeitor() {
        return idLeitor;
    }

    public void setIdLeitor(String idLeitor) {
        this.idLeitor = idLeitor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public List<LivroLista> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroLista> livros) {
        this.livros = livros;
    }

}
