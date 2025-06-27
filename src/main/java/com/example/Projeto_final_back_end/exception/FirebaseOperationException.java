package com.example.Projeto_final_back_end.exception;

public class FirebaseOperationException extends RuntimeException {
    public FirebaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FirebaseOperationException(String message) {
        super(message);
    }
}