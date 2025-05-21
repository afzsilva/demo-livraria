package com.afzdev.demo.livraria.service;

import com.afzdev.demo.livraria.dto.GeneroDTO;

import java.util.List;
import java.util.Optional;

public interface GeneroService {
    List<GeneroDTO> listarTodos();
    Optional<GeneroDTO> buscarPorId(Long id);
    GeneroDTO salvar(GeneroDTO genero);
    void excluir(Long id);
    boolean existePorId(Long id);
}
