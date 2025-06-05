package com.afzdev.demo.livraria.dto;

public class GeneroDTO {

    private Long id;

    private String nome;

    public String getNome() {
        return nome;
    }

    public GeneroDTO() {
    }

    public GeneroDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
