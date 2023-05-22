package com.example.zuccknowledge.exception;

public class EchoServiceException extends RuntimeException {
    public EchoServiceException() {
    }

    public EchoServiceException(String msg) {
        super(msg);
    }
}
