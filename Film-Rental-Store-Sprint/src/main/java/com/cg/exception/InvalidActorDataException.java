package com.cg.exception;
 
public class InvalidActorDataException extends RuntimeException {
    public InvalidActorDataException(String message) {
        super(message);
    }
}