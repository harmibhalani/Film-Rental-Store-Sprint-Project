package com.cg.exception;
 
public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException(String message) {
        super(message);
    }
}