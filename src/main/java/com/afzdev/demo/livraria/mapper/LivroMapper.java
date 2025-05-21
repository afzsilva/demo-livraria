package com.afzdev.demo.livraria.mapper;

import com.afzdev.demo.livraria.dto.LivroDTO;
import com.afzdev.demo.livraria.entities.Livro;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface LivroMapper {

    //@Mapping(target = "id", ignore = true)
    LivroDTO toDTO(Livro livroEntity);

    //@Mapping(target = "id", ignore = true)
    Livro toENTITY(LivroDTO livroDTO);

}
