package com.afzdev.demo.livraria.controller;

import com.afzdev.demo.livraria.controller.api.AutorControllerApi;
import com.afzdev.demo.livraria.dto.AutorDTO;
import com.afzdev.demo.livraria.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
public class AutorController implements AutorControllerApi {

    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> listarTodos() {
        List<AutorDTO> autores = autorService.listarTodos();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> buscarPorId(@PathVariable Long id) {
        AutorDTO autor = autorService.buscarPorId(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<AutorDTO> criar(@Valid @RequestBody AutorDTO autor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvar(autor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AutorDTO autor) {
        return ResponseEntity.ok(autorService.atualizar(id,autor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        autorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
