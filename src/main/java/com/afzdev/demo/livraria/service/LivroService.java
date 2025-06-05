package com.afzdev.demo.livraria.service;

import com.afzdev.demo.livraria.dto.LivroDTO;

import java.util.List;

public interface LivroService {
    List<LivroDTO> listarTodos();
    LivroDTO buscarPorId(Long id);
    List<LivroDTO> buscarPorAutor(Long autorId);
    List<LivroDTO> buscarPorGenero(Long generoId);
    LivroDTO salvar(LivroDTO livro);
    LivroDTO atualizar(Long id,LivroDTO livro);
    void excluir(Long id);

}