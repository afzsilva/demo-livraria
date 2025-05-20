package com.afzdev.demo.livraria.dto;

import com.afzdev.demo.livraria.entities.Autor;
import com.afzdev.demo.livraria.entities.Genero;

public class LivroDTO {

    private Long id;
    private String titulo;
    private Autor autor;
    private Genero genero;

    public LivroDTO() {
    }

    public LivroDTO(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public LivroDTO(Long id, String titulo, Autor autor, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}