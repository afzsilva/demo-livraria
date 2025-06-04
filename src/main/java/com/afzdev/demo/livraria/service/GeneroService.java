package com.afzdev.demo.livraria.service;

import com.afzdev.demo.livraria.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {
    List<GeneroDTO> listarTodos();
    GeneroDTO buscarPorId(Long id);
    GeneroDTO salvar(GeneroDTO genero);
    GeneroDTO atualizar(Long id, GeneroDTO genero);
    void excluir(Long id);
}
