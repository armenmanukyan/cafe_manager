package com.myCafe.core.service.exception;

public class InternalErrorException extends Exception{

    public InternalErrorException() {}

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalErrorException(Throwable cause) {
        super(cause);
    }
}