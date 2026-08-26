package com.example.Filmes.api_filmes.exceptions;

public class FilmeNaoEncontradoException extends RuntimeException {
    public FilmeNaoEncontradoException(String message) {
        super(message);
    }
}
