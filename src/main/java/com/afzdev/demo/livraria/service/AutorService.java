package com.afzdev.demo.livraria.service;

import com.afzdev.demo.livraria.dto.AutorDTO;

import java.util.List;

public interface AutorService {
    List<AutorDTO> listarTodos();
    AutorDTO buscarPorId(Long id);
    AutorDTO salvar(AutorDTO autor);
    AutorDTO atualizar(Long id,AutorDTO autor);
    void excluir(Long id);
}