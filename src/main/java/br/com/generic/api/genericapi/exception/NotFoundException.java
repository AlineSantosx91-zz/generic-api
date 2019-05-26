package br.com.generic.api.genericapi.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {}

    public NotFoundException(final String mensagem) {
        super(mensagem);
    }

    public NotFoundException(final String mensagem, final Throwable throwable) {
        super(mensagem, throwable);
    }

    public NotFoundException(final Throwable throwable) {
        super(throwable);
    }
}
