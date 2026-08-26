package com.example.Filmes.api_filmes.repository;

import com.example.Filmes.api_filmes.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByTitulo(String titulo);

    List<Filme> findByAnoDeLancamento(Integer anoDeLancamento);

    boolean existsByTitulo(String titulo);
}
