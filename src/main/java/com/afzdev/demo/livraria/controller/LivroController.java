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
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController implements LivroControllerApi {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarTodos() {
        List<LivroDTO> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        Optional<LivroDTO> livro = livroService.buscarPorId(id);
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LivroDTO livro) {
        if (!livroService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        livro.setId(id);
        LivroDTO livroAtualizado = livroService.salvar(livro);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!livroService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}