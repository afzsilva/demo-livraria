package com.afzdev.demo.livraria.mapper;

import com.afzdev.demo.livraria.dto.GeneroDTO;
import com.afzdev.demo.livraria.entities.Genero;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface GeneroMapper {

    //@Mapping(target = "id", ignore = true)
    GeneroDTO toDTO(Genero generoEntity);

    //@Mapping(target = "id", ignore = true)
    Genero toENTITY(GeneroDTO generoDTO);
}
