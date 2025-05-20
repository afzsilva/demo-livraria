package com.afzdev.demo.livraria.controller.api;


import com.afzdev.demo.livraria.dto.AutorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
/**
 * Interface que define a API de autores com toda a documentação Swagger
 */
@Tag(name = "Autores", description = "API para gerenciamento de autores")
public interface AutorControllerApi {

    @Operation(summary = "Listar todos os autores", description = "Retorna uma lista de todos os autores cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autores encontrados com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = AutorDTO.class))))
    })
    ResponseEntity<List<AutorDTO>> listarTodos();

    @Operation(summary = "Buscar autor por ID", description = "Retorna um autor específico com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AutorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado",
                    content = @Content)
    })
    ResponseEntity<AutorDTO> buscarPorId(
            @Parameter(description = "ID do autor", required = true) Long id);

    @Operation(summary = "Criar novo autor", description = "Cria um novo autor com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor criado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AutorDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    ResponseEntity<AutorDTO> criar(
            @Parameter(description = "Dados do autor", required = true)
            @Valid AutorDTO autor);

    @Operation(summary = "Atualizar autor", description = "Atualiza um autor existente com base no ID e dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AutorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    ResponseEntity<AutorDTO> atualizar(
            @Parameter(description = "ID do autor", required = true) Long id,
            @Parameter(description = "Dados atualizados do autor", required = true)
            @Valid AutorDTO autor);

    @Operation(summary = "Excluir autor", description = "Remove um autor existente com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor excluído com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado",
                    content = @Content)
    })
    ResponseEntity<Void> excluir(
            @Parameter(description = "ID do autor", required = true) Long id);
}