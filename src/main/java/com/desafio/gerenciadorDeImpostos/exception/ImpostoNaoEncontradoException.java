package com.desafio.gerenciadorDeImpostos.exception;

public class ImpostoNaoEncontradoException extends RuntimeException {
    public ImpostoNaoEncontradoException(String message) {
        super(message);
    }
}
