package com.afzdev.demo.livraria.service.impl;

import com.afzdev.demo.livraria.dto.AutorDTO;
import com.afzdev.demo.livraria.entities.Autor;
import com.afzdev.demo.livraria.exceptions.DataBaseException;
import com.afzdev.demo.livraria.exceptions.ResourceNotFoundException;
import com.afzdev.demo.livraria.mapper.AutorMapper;
import com.afzdev.demo.livraria.repository.AutorRepository;
import com.afzdev.demo.livraria.service.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public AutorDTO buscarPorId(Long id) {
        return autorRepository.findById(id)
                .map(autorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado Autor com id : "+id));
    }


    @Override
    @Transactional
    public AutorDTO salvar(AutorDTO autor) {
        return autorMapper.toDTO(autorRepository.save( autorMapper.toENTITY(autor)));
    }

    @Override
    public AutorDTO atualizar(Long id, AutorDTO autorDto) {
        try {
            Autor autor = autorRepository.getReferenceById(id);
            updateToEntity(autorDto,autor);
            return autorMapper.toDTO(autorRepository.save(autor));
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Não foi encontrado Autor com id : "+id);
        }
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        try{
            if (!autorRepository.existsById(id)){
                throw new ResourceNotFoundException("Não foi encontrado Autor com "+id +" ao tentar excluir");
            }
            autorRepository.deleteById(id);
        }catch (
                DataIntegrityViolationException e){
            throw new DataBaseException("Falha de integridade referencial");
        }
    }

    //auxiliares
    private void updateToEntity(AutorDTO dto, Autor entity){
        entity.setNome(dto.getNome());
    }
}
