package com.example.Filmes.api_filmes.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dados de um filme retornados pela API")
public record FilmeResponse(

        @Schema(description = "Identificador único do filme", example = "1")
        Long id,

        @Schema(description = "Título do filme", example = "ACADEMY DINOSAUR")
        String titulo,

        @Schema(description = "Descrição/sinopse do filme", example = "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies")
        String descricao,

        @Schema(description = "Ano de lançamento do filme", example = "2006")
        Integer anoDeLancamento,

        @Schema(description = "Idioma em que o filme está disponível")
        IdiomaResponse idioma,

        @Schema(description = "Duração da locação em dias", example = "6")
        int duracaoLocacao,

        @Schema(description = "Preço da locação do filme", example = "0.99")
        double precoLocacao,

        @Schema(description = "Duração do filme em minutos", example = "86")
        int duracaoFilme,

        @Schema(description = "Custo de substituição do filme em caso de perda ou dano", example = "20.99")
        double custoDeSubstituicao,

        @Schema(description = "Classificação indicativa do filme", example = "G")
        String classificacao,

        @Schema(description = "Recursos especiais disponíveis no filme", example = "TRAILERS")
        String recursosEspeciais,

        @Schema(description = "Data e hora da última atualização do registro", example = "2006-02-15 05:03:42")
        LocalDateTime ultimaAtualizacao

) {

}