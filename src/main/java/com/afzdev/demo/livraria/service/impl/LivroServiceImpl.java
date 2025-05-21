package com.afzdev.demo.livraria.service.impl;

import com.afzdev.demo.livraria.dto.LivroDTO;
import com.afzdev.demo.livraria.mapper.LivroMapper;
import com.afzdev.demo.livraria.repository.LivroRepository;
import com.afzdev.demo.livraria.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    @Autowired
    public LivroServiceImpl(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroDTO> listarTodos() {
        return livroRepository.findAll().stream().map(livro -> livroMapper.toDTO(livro)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LivroDTO> buscarPorId(Long id) {
        return Optional.of( livroMapper.toDTO(livroRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Não foi encontrado Livro com id : "+id))));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroDTO> buscarPorAutor(Long autorId) {
        return livroRepository.findByAutorId(autorId).stream()
                .map(livro -> livroMapper.toDTO(livro)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroDTO> buscarPorGenero(Long generoId) {
        return livroRepository.findByGeneroId(generoId).stream()
                .map(livro -> livroMapper.toDTO(livro)).toList();
    }

    @Override
    @Transactional
    public LivroDTO salvar(LivroDTO livro) {
        return livroMapper.toDTO(livroRepository.save(livroMapper.toENTITY(livro)));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        livroRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return livroRepository.existsById(id);
    }
}
