package com.cg.exception;
 
public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(String message) {
        super(message);
    }
}