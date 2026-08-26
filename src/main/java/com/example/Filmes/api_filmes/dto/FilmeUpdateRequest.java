package com.example.Filmes.api_filmes.dto;

import com.example.Filmes.api_filmes.entity.Idioma;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

/**
 * Dados necessários para atualizar um filme existente
 *
 * @param titulo título do filme
 * @param descricao sinopse/descrição do filme
 * @param anoDeLancamento ano de lançamento do filme
 * @param idioma identificador do idioma em que o filme está disponível
 * @param idiomaOriginal identificador do idioma original do filme
 * @param duracaoLocacao duração da locação em dias
 * @param precoLocacao preço cobrado pela locação do filme
 * @param duracaoFilme duração do filme em minutos
 * @param custoDeSubstituicao custo de substituição do filme em caso de perda ou dano
 * @param classificacao classificação indicativa do filme
 * @param recursosEspeciais recursos especiais disponíveis no filme
 * */
@Schema(description = "Dados utilizados para atualizar um filme")

public record FilmeUpdateRequest(

        @Schema(
                description = "Título do filme",
                example = "ACADEMY DINOSAUR"
        )
        @NotBlank(message = "o título é obrigatório")
        @Size(min = 1, max = 255, message = "o título deve possuir entre 1 e 255 caracteres")
        String titulo,

        @Schema(
                description = "Descrição/sinopse do filme",
                example = "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies"
        )
        @NotBlank(message = "a descrição é obrigatória")
        String descricao,

        @Schema(
                description = "Ano de lançamento do filme",
                example = "2006"
        )
        @Min(value = 1888, message = "o ano de lançamento deve ser igual ou posterior a 1888")
        @Max(value = 2100, message = "o ano de lançamento deve ser um valor válido")
        Integer anoDeLancamento,

        @Schema(
                description = "Identificador do idioma em que o filme está disponível",
                example = "1"
        )
        @NotNull(message = "o idioma é obrigatório")
        Idioma idioma,

        @Schema(
                description = "Identificador do idioma original do filme",
                example = "2"
        )
        @NotNull(message = "o idioma original é obrigatório")
        Idioma idiomaOriginal,

        @Schema(
                description = "Duração da locação em dias",
                example = "5"
        )
        @Min(value = 1, message = "a duração da locação deve ser de no mínimo 1 dia")
        int duracaoLocacao,

        @Schema(
                description = "Preço da locação do filme",
                example = "0.99"
        )
        @NotNull(message = "o preço de locação é obrigatório")
        @Positive(message = "o preço de locação deve ser maior que zero")
        Double precoLocacao,

        @Schema(
                description = "Duração do filme em minutos",
                example = "86"
        )
        @Min(value = 1, message = "a duração do filme deve ser maior que zero")
        int duracaoFilme,

        @Schema(
                description = "Custo de substituição do filme em caso de perda ou dano",
                example = "20.99"
        )
        @NotNull(message = "o custo de substituição é obrigatório")
        @PositiveOrZero(message = "o custo de substituição não pode ser negativo")
        Double custoDeSubstituicao,

        @Schema(
                description = "Classificação indicativa do filme",
                example = "G"
        )
        @NotNull(message = "a classificação é obrigatória")
        @Pattern(regexp = "G|PG|PG-13|R|NC-17", message = "classificação inválida")
        String classificacao,

        @Schema(
                description = "Recursos especiais disponíveis no filme",
                example = "Trailers"
        )
        @NotNull(message = "os recursos especiais são obrigatórios")
        String recursosEspeciais

) { }