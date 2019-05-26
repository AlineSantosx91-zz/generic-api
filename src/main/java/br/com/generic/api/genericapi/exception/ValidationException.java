package br.com.generic.api.genericapi.exception;

public class ValidationException extends RuntimeException {
    public ValidationException() {}

    public ValidationException(final String mensagem) {
        super(mensagem);
    }

    public ValidationException(final String mensagem, final Throwable throwable) {
        super(mensagem, throwable);
    }

    public ValidationException(final Throwable throwable) {
        super(throwable);
    }
}
