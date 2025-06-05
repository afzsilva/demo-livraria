package com.afzdev.demo.livraria.factory;

import com.afzdev.demo.livraria.dto.AutorDTO;
import com.afzdev.demo.livraria.dto.GeneroDTO;
import com.afzdev.demo.livraria.dto.LivroDTO;
import com.afzdev.demo.livraria.entities.Autor;
import com.afzdev.demo.livraria.entities.Genero;
import com.afzdev.demo.livraria.entities.Livro;

public class LivroFactory {

    public static Livro getLivro(){
        var autor = new Autor(1L,"Kent Beck");
        var genero = new Genero(1L,"Programação");
        return new Livro(1L,"Desenvolvimento Guiado Por Testes",autor,genero);
    }

    public static LivroDTO getLivroDTO(){
        var autor = new AutorDTO(1L,"Kent Beck");
        var genero = new GeneroDTO(1L,"Programação");
        return new LivroDTO(1L,"Desenvolvimento Guiado Por Testes",autor,genero);
    }
}
