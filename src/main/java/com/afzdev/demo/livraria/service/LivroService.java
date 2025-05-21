package com.afzdev.demo.livraria.service;

import com.afzdev.demo.livraria.dto.LivroDTO;

import java.util.List;
import java.util.Optional;

public interface LivroService {
    List<LivroDTO> listarTodos();
    Optional<LivroDTO> buscarPorId(Long id);
    List<LivroDTO> buscarPorAutor(Long autorId);
    List<LivroDTO> buscarPorGenero(Long generoId);
    LivroDTO salvar(LivroDTO livro);
    void excluir(Long id);
    boolean existePorId(Long id);
}