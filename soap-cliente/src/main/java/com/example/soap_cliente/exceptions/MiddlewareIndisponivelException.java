package com.example.soap_cliente.exceptions;

public class MiddlewareIndisponivelException extends RuntimeException {
    public MiddlewareIndisponivelException(String message, Throwable cause) {
        super(message, cause);
    }
}