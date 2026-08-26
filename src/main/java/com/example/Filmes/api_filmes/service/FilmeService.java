package com.example.Filmes.api_filmes.service;

import com.example.Filmes.api_filmes.dto.FilmeCreateRequest;
import com.example.Filmes.api_filmes.dto.FilmeResponse;
import com.example.Filmes.api_filmes.dto.FilmeUpdateRequest;
import com.example.Filmes.api_filmes.entity.Filme;
import com.example.Filmes.api_filmes.exceptions.FilmeNaoEncontradoException;
import com.example.Filmes.api_filmes.mapper.FilmeMapper;
import com.example.Filmes.api_filmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FilmeService {
    private final FilmeRepository repository;
    private final FilmeMapper mapper;

    public FilmeService(FilmeRepository repository, FilmeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public FilmeResponse cadastrar (FilmeCreateRequest request) {
        if(repository.existsByTitulo(request.titulo())) {
            throw new IllegalArgumentException("Já existe um filme com esse titulo");
        }
        Filme filme = mapper.toEntity(request);
        filme.setUltimaAtualizacao(LocalDateTime.now());
        Filme salvo = repository.save(filme);

        return mapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public List<FilmeResponse> listar() {
        List<Filme> filmes = repository.findAll();

        return mapper.toResponseList(filmes);
    }

    @Transactional
    public FilmeResponse buscarPorId (Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<FilmeResponse> buscarPorTitulo (String titulo) {
        return repository.findByTitulo(titulo)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FilmeResponse> buscarPorAnoDeLancamento (Integer anoLancamento) {
        return repository.findByAnoDeLancamento(anoLancamento)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public FilmeResponse atualizar(Long id, FilmeUpdateRequest request) {
        Filme filme = repository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado com ID: " + id));
        mapper.updateEntity(request, filme);
        Filme atualizado = repository.save(filme);
        return mapper.toResponse(atualizado);
    }

    @Transactional
    public void remover (Long id) {
        Filme filme =repository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado com ID: " + id));
        repository.delete(filme);
    }

}
