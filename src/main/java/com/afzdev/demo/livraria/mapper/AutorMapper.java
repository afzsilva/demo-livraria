package com.afzdev.demo.livraria.mapper;

import com.afzdev.demo.livraria.dto.AutorDTO;
import com.afzdev.demo.livraria.entities.Autor;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface AutorMapper {
    //@Mapping(target = "id", ignore = true)
    AutorDTO toDTO(Autor autorEntity);

    //@Mapping(target = "id", ignore = true)
    Autor toENTITY(AutorDTO autorDTO);
}