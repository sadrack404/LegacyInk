package com.legacyInk.domain.exception;

public class EnderecoIdNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public EnderecoIdNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EnderecoIdNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de usuario com o código de id: %d", id));
    }

}
