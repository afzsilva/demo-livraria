package com.afzdev.demo.livraria.controller;


import com.afzdev.demo.livraria.controller.api.GeneroControllerApi;
import com.afzdev.demo.livraria.dto.GeneroDTO;
import com.afzdev.demo.livraria.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/generos")
public class GeneroController implements GeneroControllerApi {

    private final GeneroService generoService;

    @Autowired
    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listarTodos() {
        List<GeneroDTO> generos = generoService.listarTodos();
        return ResponseEntity.ok(generos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> buscarPorId(@PathVariable Long id) {
        Optional<GeneroDTO> genero = generoService.buscarPorId(id);
        return genero.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> criar(@Valid @RequestBody GeneroDTO genero) {
        GeneroDTO novoGenero = generoService.salvar(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoGenero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> atualizar(@PathVariable Long id, @Valid @RequestBody GeneroDTO genero) {
        if (!generoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        genero.setId(id);
        GeneroDTO generoAtualizado = generoService.salvar(genero);
        return ResponseEntity.ok(generoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!generoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        generoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
