package com.RamenGo.RamenGo.exceptions;

public class ApiKeyMissingException extends RuntimeException{
    public ApiKeyMissingException() {
        super("x-api-key header missing");
    }
}

