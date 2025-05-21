package com.afzdev.demo.livraria.repository;

import com.afzdev.demo.livraria.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutorId(Long autorId);
    List<Livro> findByGeneroId(Long generoId);
}
