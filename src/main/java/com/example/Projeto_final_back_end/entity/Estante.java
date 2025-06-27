package com.example.Projeto_final_back_end.entity;


import java.time.LocalDateTime;

public class Estante {
    private String id;
    private String usuarioId;
    private String bibliotecaId;
    private ListaLivro listaLivro;
    private String descricao;
    private String tema;
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

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getBibliotecaId() {
        return bibliotecaId;
    }

    public void setBibliotecaId(String bibliotecaId) {
        this.bibliotecaId = bibliotecaId;
    }

    public ListaLivro getListaLivro() {
        return listaLivro;
    }

    public void setListaLivro(ListaLivro listaLivro) {
        this.listaLivro = listaLivro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }


}
