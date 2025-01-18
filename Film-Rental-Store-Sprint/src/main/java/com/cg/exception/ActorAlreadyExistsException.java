package com.cg.exception;
 
public class ActorAlreadyExistsException extends RuntimeException {
    public ActorAlreadyExistsException(String message) {
        super(message);
    }
}