package com.cg.exception;
 
public class FilmAlreadyAssignedException extends RuntimeException {
 
    public FilmAlreadyAssignedException() {
        super();
    }
 
    public FilmAlreadyAssignedException(String message) {
        super(message);
    }
 
    public FilmAlreadyAssignedException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public FilmAlreadyAssignedException(Throwable cause) {
        super(cause);
    }
}