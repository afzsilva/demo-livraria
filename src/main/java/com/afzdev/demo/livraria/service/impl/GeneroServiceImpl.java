package com.afzdev.demo.livraria.service.impl;

import com.afzdev.demo.livraria.dto.AutorDTO;
import com.afzdev.demo.livraria.dto.GeneroDTO;
import com.afzdev.demo.livraria.entities.Autor;
import com.afzdev.demo.livraria.entities.Genero;
import com.afzdev.demo.livraria.exceptions.DataBaseException;
import com.afzdev.demo.livraria.exceptions.ResourceNotFoundException;
import com.afzdev.demo.livraria.mapper.GeneroMapper;
import com.afzdev.demo.livraria.repository.GeneroRepository;
import com.afzdev.demo.livraria.service.GeneroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public GeneroDTO buscarPorId(Long id) {
        return generoRepository.findById(id)
                .map(generoMapper::toDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Não foi encontrado Autor com id : "+id));
    }


    @Override
    @Transactional
    public GeneroDTO salvar(GeneroDTO genero) {
        return generoMapper.toDTO(generoRepository.save(generoMapper.toENTITY(genero)));
    }


    @Override
    public GeneroDTO atualizar(Long id,GeneroDTO generoDTO) {
        try {
            Genero genero = generoRepository.getReferenceById(id);
            updateToEntity(generoDTO,genero);
            return generoMapper.toDTO(generoRepository.save(genero));
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Não foi encontrado Genero com id : "+id);
        }
    }


    @Override
    @Transactional
    public void excluir(Long id) {
        try{
            if (!generoRepository.existsById(id)){
                throw new ResourceNotFoundException("Não foi encontrado Genero com "+id+" ao tentar excluir");
            }
            generoRepository.deleteById(id);
        }catch (
                DataIntegrityViolationException e){
            throw new DataBaseException("Falha de integridade referencial");
        }

    }

    //auxiliares
    private void updateToEntity(GeneroDTO dto, Genero entity){
        entity.setNome(dto.getNome());
    }
}
