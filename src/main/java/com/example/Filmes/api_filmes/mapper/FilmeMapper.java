package com.example.Filmes.api_filmes.mapper;

import com.example.Filmes.api_filmes.dto.FilmeCreateRequest;
import com.example.Filmes.api_filmes.dto.FilmeResponse;
import com.example.Filmes.api_filmes.dto.FilmeUpdateRequest;
import com.example.Filmes.api_filmes.dto.IdiomaResponse;
import com.example.Filmes.api_filmes.entity.Filme;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmeMapper {
    public Filme toEntity (FilmeCreateRequest request) {
        return Filme.builder()
                .titulo(request.titulo())
                .descricao(request.descricao())
                .anoDeLancamento(request.anoDeLancamento())
                .idioma(request.idioma())
                .idiomaOriginal(request.idiomaOriginal())
                .duracaoLocacao(request.duracaoLocacao())
                .precoLocacao(request.precoLocacao())
                .duracaoFilme(request.duracaoFilme())
                .custoDeSubstituicao(request.custoDeSubstituicao())
                .classificacao(request.classificacao())
                .recursosEspeciais(request.recursosEspeciais())
                .build();
    }
    public FilmeResponse toResponse (Filme filme) {
        return new FilmeResponse(
                filme.getId(),
                filme.getTitulo(),
                filme.getDescricao(),
                filme.getAnoDeLancamento(),
                IdiomaResponse.fromEntity(filme.getIdioma()),
                filme.getDuracaoLocacao(),
                filme.getPrecoLocacao(),
                filme.getDuracaoFilme(),
                filme.getCustoDeSubstituicao(),
                filme.getClassificacao(),
                filme.getRecursosEspeciais(),
                filme.getUltimaAtualizacao()
        );
    }

    public List<FilmeResponse> toResponseList (List<Filme> filmes) {
        return filmes.stream()
                .map(this::toResponse)
                .toList();
    }

    public void updateEntity(FilmeUpdateRequest request, Filme filme) {
        filme.setTitulo(request.titulo());
        filme.setDescricao(request.descricao());
        filme.setAnoDeLancamento(request.anoDeLancamento());
        filme.setIdioma(request.idioma());
        filme.setIdiomaOriginal(request.idiomaOriginal());
        filme.setDuracaoLocacao(request.duracaoLocacao());
        filme.setPrecoLocacao(request.precoLocacao());
        filme.setDuracaoFilme(request.duracaoFilme());
        filme.setCustoDeSubstituicao(request.custoDeSubstituicao());
        filme.setClassificacao(request.classificacao());
        filme.setRecursosEspeciais(request.recursosEspeciais());
    }
}
