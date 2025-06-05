package com.afzdev.demo.livraria.service.impl;

import com.afzdev.demo.livraria.dto.LivroDTO;
import com.afzdev.demo.livraria.entities.Livro;
import com.afzdev.demo.livraria.exceptions.ResourceNotFoundException;
import com.afzdev.demo.livraria.mapper.AutorMapper;
import com.afzdev.demo.livraria.mapper.GeneroMapper;
import com.afzdev.demo.livraria.mapper.LivroMapper;
import com.afzdev.demo.livraria.repository.LivroRepository;
import com.afzdev.demo.livraria.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;
    private final GeneroMapper generoMapper;
    private final AutorMapper autorMapper;

    @Autowired
    public LivroServiceImpl(LivroRepository livroRepository, LivroMapper livroMapper, GeneroMapper generoMapper, AutorMapper autorMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
        this.generoMapper = generoMapper;
        this.autorMapper = autorMapper;
    }

    //TODO add paginação
    @Override
    @Transactional(readOnly = true)
    public List<LivroDTO> listarTodos() {
        return livroRepository.findAll().stream().map(livro -> livroMapper.toDTO(livro)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LivroDTO buscarPorId(Long id) {
        return livroRepository.findById(id)
                .map(livroMapper::toDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi encontrado Livro com id : "+id));
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
    public LivroDTO atualizar(Long id, LivroDTO livroDTO) {
        try {
            Livro livro = livroRepository.getReferenceById(id);
            updateToEntity(livroDTO,livro);
            return livroMapper.toDTO(livroRepository.save(livro));
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Não foi encontrado Livro com id : "+id);
        }
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isEmpty()) {
            throw new ResourceNotFoundException("Não foi encontrado Livro com id : " + id);
        }
        livroRepository.deleteById(id);
    }

    //auxiliares
    private void updateToEntity(LivroDTO dto, Livro entity){
        entity.setTitulo(dto.getTitulo());
        entity.setGenero(generoMapper.toENTITY(dto.getGenero()));
        entity.setAutor(autorMapper.toENTITY(dto.getAutor()));
    }
}
