package com.afzdev.demo.livraria.controller.api;

import com.afzdev.demo.livraria.dto.LivroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;

/**
 * Interface que define a API de livros com toda a documentação Swagger
 */
@Tag(name = "Livros", description = "API para gerenciamento de livros")
public interface LivroControllerApi {

    @Operation(summary = "Listar todos os livros", description = "Retorna uma lista de todos os livros cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros encontrados com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = LivroDTO.class))))
    })
    ResponseEntity<List<LivroDTO>> listarTodos();

    @Operation(summary = "Buscar livro por ID", description = "Retorna um livro específico com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LivroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado",
                    content = @Content)
    })
    ResponseEntity<LivroDTO> buscarPorId(
            @Parameter(description = "ID do livro", required = true) Long id);

    @Operation(summary = "Buscar livros por autor", description = "Retorna uma lista de livros de um determinado autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros encontrados com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = LivroDTO.class))))
    })
    ResponseEntity<List<LivroDTO>> buscarPorAutor(
            @Parameter(description = "ID do autor", required = true) Long autorId);

    @Operation(summary = "Buscar livros por gênero", description = "Retorna uma lista de livros de um determinado gênero literário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros encontrados com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = LivroDTO.class))))
    })
    ResponseEntity<List<LivroDTO>> buscarPorGenero(
            @Parameter(description = "ID do gênero", required = true) Long generoId);

    @Operation(summary = "Criar novo livro", description = "Cria um novo livro com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LivroDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    ResponseEntity<LivroDTO> criar(
            @Parameter(description = "Dados do livro", required = true)
            @Valid LivroDTO livro);

    @Operation(summary = "Atualizar livro", description = "Atualiza um livro existente com base no ID e dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LivroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    ResponseEntity<LivroDTO> atualizar(
            @Parameter(description = "ID do livro", required = true) Long id,
            @Parameter(description = "Dados atualizados do livro", required = true)
            @Valid LivroDTO livro);

    @Operation(summary = "Excluir livro", description = "Remove um livro existente com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro excluído com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado",
                    content = @Content)
    })
    ResponseEntity<Void> excluir(
            @Parameter(description = "ID do livro", required = true) Long id);
}