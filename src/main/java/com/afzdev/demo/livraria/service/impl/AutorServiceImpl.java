package com.afzdev.demo.livraria.service.impl;

import com.afzdev.demo.livraria.dto.AutorDTO;
import com.afzdev.demo.livraria.mapper.AutorMapper;
import com.afzdev.demo.livraria.repository.AutorRepository;
import com.afzdev.demo.livraria.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;

    @Autowired
    public AutorServiceImpl(AutorRepository autorRepository, AutorMapper mapper) {
        this.autorRepository = autorRepository;
        this.autorMapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutorDTO> listarTodos() {
        return autorRepository.findAll().stream()
                .map(autor -> autorMapper.toDTO(autor))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AutorDTO> buscarPorId(Long id) {
        return Optional.of(autorMapper.toDTO(autorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Não foi encontrado Autor com id : "+id))));
    }

    @Override
    @Transactional
    public AutorDTO salvar(AutorDTO autor) {
        return autorMapper.toDTO(autorRepository.save( autorMapper.toENTITY(autor)));
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        autorRepository.deleteById(id);
    }



    @Override
    public boolean existePorId(Long id) {
        return autorRepository.existsById(id);
    }
}
