package com.afzdev.demo.livraria.service;

import com.afzdev.demo.livraria.dto.AutorDTO;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    List<AutorDTO> listarTodos();
    Optional<AutorDTO> buscarPorId(Long id);
    AutorDTO salvar(AutorDTO autor);
    void excluir(Long id);
    boolean existePorId(Long id);
}