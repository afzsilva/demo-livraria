package com.afzdev.demo.livraria.controller.api;

import com.afzdev.demo.livraria.dto.GeneroDTO;
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
 * Interface que define a API de gêneros literários com toda a documentação Swagger
 */
@Tag(name = "Gêneros", description = "API para gerenciamento de gêneros literários")
public interface GeneroControllerApi {

    @Operation(summary = "Listar todos os gêneros", description = "Retorna uma lista de todos os gêneros cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gêneros encontrados com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GeneroDTO.class))))
    })
    ResponseEntity<List<GeneroDTO>> listarTodos();

    @Operation(summary = "Buscar gênero por ID", description = "Retorna um gênero específico com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero encontrado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GeneroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado",
                    content = @Content)
    })
    ResponseEntity<GeneroDTO> buscarPorId(
            @Parameter(description = "ID do gênero", required = true) Long id);

    @Operation(summary = "Criar novo gênero", description = "Cria um novo gênero com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gênero criado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GeneroDTO.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    ResponseEntity<GeneroDTO> criar(
            @Parameter(description = "Dados do gênero", required = true)
            @Valid GeneroDTO genero);

    @Operation(summary = "Atualizar gênero", description = "Atualiza um gênero existente com base no ID e dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero atualizado com sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GeneroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    ResponseEntity<GeneroDTO> atualizar(
            @Parameter(description = "ID do gênero", required = true) Long id,
            @Parameter(description = "Dados atualizados do gênero", required = true)
            @Valid GeneroDTO genero);

    @Operation(summary = "Excluir gênero", description = "Remove um gênero existente com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Gênero excluído com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado",
                    content = @Content)
    })
    ResponseEntity<Void> excluir(
            @Parameter(description = "ID do gênero", required = true) Long id);
}
