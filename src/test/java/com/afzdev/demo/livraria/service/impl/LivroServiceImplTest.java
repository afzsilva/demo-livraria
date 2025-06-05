package com.afzdev.demo.livraria.service.impl;

import com.afzdev.demo.livraria.dto.LivroDTO;
import com.afzdev.demo.livraria.entities.Livro;
import com.afzdev.demo.livraria.exceptions.ResourceNotFoundException;
import com.afzdev.demo.livraria.factory.LivroFactory;
import com.afzdev.demo.livraria.mapper.AutorMapper;
import com.afzdev.demo.livraria.mapper.GeneroMapper;
import com.afzdev.demo.livraria.mapper.LivroMapper;
import com.afzdev.demo.livraria.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LivroServiceImplTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private LivroMapper livroMapper;

    @Mock
    private AutorMapper autorMapper;

    @Mock
    private GeneroMapper generoMapper;


    @InjectMocks
    private LivroServiceImpl livroService;

    private Livro livro;
    private LivroDTO livroDTO;
    private List<Livro> livroList;
    private Long idExistente;
    private Long idNaoExistente;

    @BeforeEach
    void setup(){
        livro = LivroFactory.getLivro();
        livroDTO = LivroFactory.getLivroDTO();
        livroList = List.of(livro);
        idExistente = 1L;

        when(livroRepository.findAll()).thenReturn(livroList);
        when(livroRepository.findById(idExistente)).thenReturn(Optional.of(livro));
        when(livroMapper.toDTO(livro)).thenReturn(livroDTO);
        when(livroRepository.findByAutorId(idExistente)).thenReturn(livroList);
        when(livroRepository.findByGeneroId(idExistente)).thenReturn(livroList);
        when(livroRepository.save(any())).thenReturn(livro);
        when(livroRepository.getReferenceById(idExistente)).thenReturn(livro);
        when(livroRepository.getReferenceById(idNaoExistente)).thenThrow(EntityNotFoundException.class);
        doReturn(true).when(livroRepository).existsById(idExistente);
        doNothing().when(livroRepository).deleteById(idExistente);

    }

    @Test
    void listarTodos() {
       List<LivroDTO> result = livroService.listarTodos();
       Assertions.assertNotNull(result);
    }

    @Test
    void buscarPorId() {
       LivroDTO result = livroService.buscarPorId(idExistente);
       Assertions.assertNotNull(result);
       Assertions.assertEquals(result.getId(), idExistente);
    }

    @Test
    void buscarPorAutor() {
       List<LivroDTO> results = livroService.buscarPorAutor(idExistente);
       Assertions.assertNotNull(results);
       Assertions.assertTrue(results.size() == 1);
    }

    @Test
    void buscarPorGenero() {
        List<LivroDTO> results = livroService.buscarPorGenero(idExistente);
        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.size() == 1);
    }

    @Test
    void salvar() {
        LivroDTO result = livroService.salvar(livroDTO);
        Assertions.assertEquals(result.getId(), idExistente);
    }

    @Test
    void atualizar() {
       LivroDTO result = livroService.atualizar(idExistente, livroDTO);
       Assertions.assertNotNull(result);
       Assertions.assertTrue(result.getId() == 1L);
    }

    @Test
    void deveLancarResourceNotFoundExceptionAoTentarAtualizarComIdInexistente() {
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            livroService.atualizar(idNaoExistente, livroDTO);
        });
    }

    @Test
    void excluir() {

        Assertions.assertDoesNotThrow(()->{
            livroService.excluir(idExistente);
        });
        verify(livroRepository, times(1)).deleteById(idExistente);
    }

    @Test
    void deveLancarResourceNotFoundExceptionAoTentarDeletarComIdInexistente() {
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            livroService.excluir(idNaoExistente);
        });
    }
}