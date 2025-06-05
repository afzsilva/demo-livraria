package com.afzdev.demo.livraria.controller;

import com.afzdev.demo.livraria.controller.api.LivroControllerApi;
import com.afzdev.demo.livraria.dto.LivroDTO;
import com.afzdev.demo.livraria.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroController implements LivroControllerApi {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    //TODO add paginação
    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarTodos() {
        List<LivroDTO> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<LivroDTO>> buscarPorAutor(@PathVariable Long autorId) {
        List<LivroDTO> livros = livroService.buscarPorAutor(autorId);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/genero/{generoId}")
    public ResponseEntity<List<LivroDTO>> buscarPorGenero(@PathVariable Long generoId) {
        List<LivroDTO> livros = livroService.buscarPorGenero(generoId);
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    public ResponseEntity<LivroDTO> criar(@Valid @RequestBody LivroDTO livro) {
        LivroDTO novoLivro = livroService.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.ok(livroService.atualizar(id,livroDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}