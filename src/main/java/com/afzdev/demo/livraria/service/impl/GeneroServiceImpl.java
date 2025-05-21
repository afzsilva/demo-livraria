package com.afzdev.demo.livraria.service.impl;

import com.afzdev.demo.livraria.dto.GeneroDTO;
import com.afzdev.demo.livraria.mapper.GeneroMapper;
import com.afzdev.demo.livraria.repository.GeneroRepository;
import com.afzdev.demo.livraria.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;

    @Autowired
    public GeneroServiceImpl(GeneroRepository generoRepository, GeneroMapper generoMapper) {
        this.generoRepository = generoRepository;
        this.generoMapper = generoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GeneroDTO> listarTodos() {
        return generoRepository.findAll().stream().map(genero -> generoMapper.toDTO(genero)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GeneroDTO> buscarPorId(Long id) {
        return Optional.of(generoMapper.toDTO(generoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Não foi encontrado Genero com id : "+id))));

    }

    @Override
    @Transactional
    public GeneroDTO salvar(GeneroDTO genero) {
        return generoMapper.toDTO(generoRepository.save(generoMapper.toENTITY(genero)));
    }


    @Override
    @Transactional
    public void excluir(Long id) {
        generoRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return generoRepository.existsById(id);
    }
}
