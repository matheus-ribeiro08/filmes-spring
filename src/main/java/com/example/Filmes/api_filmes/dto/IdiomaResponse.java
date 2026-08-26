package com.example.Filmes.api_filmes.dto;

import com.example.Filmes.api_filmes.entity.Idioma;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de um idioma retornados pela API")
public record IdiomaResponse(

        @Schema(description = "Identificador único do idioma", example = "1")
        Long id,

        @Schema(description = "Nome do idioma", example = "Português")
        String nome
) {
        public static IdiomaResponse fromEntity(Idioma idioma) {
                if (idioma == null) {
                        return null;
                }
                return new IdiomaResponse(
                        idioma.getId(),
                        idioma.getNome()
                );
        }
}