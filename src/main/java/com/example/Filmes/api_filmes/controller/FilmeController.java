package com.example.Filmes.api_filmes.controller;

import com.example.Filmes.api_filmes.dto.FilmeResponse;
import com.example.Filmes.api_filmes.service.FilmeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.Filmes.api_filmes.dto.ErroResponse;
import com.example.Filmes.api_filmes.dto.FilmeCreateRequest;
import com.example.Filmes.api_filmes.dto.FilmeResponse;
import com.example.Filmes.api_filmes.dto.FilmeUpdateRequest;
import com.example.Filmes.api_filmes.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(
        name = "Filmes",
        description = "Operações relacionadas ao gerenciamento de filmes"
)
@RestController
@RequestMapping("/api/v1/filmes")
public class FilmeController {
    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }


    @Operation(
            summary = "Lista de filmes",
            description = "Retorna todos os filmes cadastrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Filmes retornados com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<FilmeResponse>> listar(){
        return ResponseEntity.ok(service.listar());
    }


    @Operation(
            summary = "Busca filme por ID",
            description = "Retorna os detalhes de um filme específico com base no seu identificador único"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Filme encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Filme não encontrado",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> buscarPorId(
            @Parameter(description = "Identificador único do filme", example = "1")
            @PathVariable Long id
    ){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Busca filmes por título",
            description = "Retorna uma lista de filmes cujo título corresponda ao informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta realizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro de busca inválido",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            )
    })
    @GetMapping(params = "titulo")
    public ResponseEntity<List<FilmeResponse>> buscarPorTitulo(
            @Parameter(description = "Título ou parte do título do filme", example = "Academy Dinosaur")
            @RequestParam String titulo
    ) {
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }


    @Operation(
            summary = "Busca filmes por ano de lançamento",
            description = "Retorna uma lista de filmes lançados no ano informado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta realizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro de busca inválido",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            )
    })
    @GetMapping(params = "anoLancamento")
    public ResponseEntity<List<FilmeResponse>> buscarPorAnoDeLancamento(
            @Parameter(description = "Ano de lançamento do filme", example = "2006")
            @RequestParam Integer anoLancamento
    ) {
        return ResponseEntity.ok(service.buscarPorAnoDeLancamento(anoLancamento));
    }

    @Operation(
            summary = "Cadastro de um filme",
            description = "Cria um novo filme no catálogo e retorna o recurso criado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Filme criado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<FilmeResponse> cadastrar (@Valid @RequestBody FilmeCreateRequest request) {
        FilmeResponse filme = service.cadastrar(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(filme.id())
                .toUri();
        return ResponseEntity.created(uri).body(filme);
    }


    @Operation(
            summary = "Atualização de um filme",
            description = "Atualiza os dados de um filme existente com base no seu identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Filme atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Filme não encontrado",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponse> atualizar(
            @Parameter(description = "Identificador único do filme", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody FilmeUpdateRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @Operation (
            summary = "Remove um filme",
            description = "Realiza a exclusão do filme com base no seu identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Filme removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Filme não encontrado",
                    content = @Content(schema = @Schema(implementation = ErroResponse.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @Parameter (description = "Identificador único do filme", example = "1")
            @PathVariable Long id
    ) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}
