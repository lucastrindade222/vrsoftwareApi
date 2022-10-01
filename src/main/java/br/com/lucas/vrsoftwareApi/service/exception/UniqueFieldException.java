package br.com.lucas.vrsoftwareApi.service.exception;

public class UniqueFieldException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UniqueFieldException(String msg) {
        super(msg);
    }

    public UniqueFieldException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
