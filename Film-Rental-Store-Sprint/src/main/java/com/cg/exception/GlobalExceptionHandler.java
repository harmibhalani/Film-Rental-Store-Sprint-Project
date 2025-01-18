package com.cg.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle EntityNotFoundException
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
	    CustomErrorResponse error = new CustomErrorResponse(
	        LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}


    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("details", request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Handle Generic Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException1(Exception ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", "An unexpected error occurred");
        errorDetails.put("details", request.getDescription(false));
        errorDetails.put("error", ex.getClass().getName()); // Add exception class for debugging

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Handle Resource Not Found Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("details", request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    // Handle MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", "Validation Failed");
        errorDetails.put("errors", errors);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
 // Handle MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid Argument");
        response.put("message", "The argument '" + ex.getName() + "' must be of type " + ex.getRequiredType().getSimpleName());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    

    //Customer Exceptions
    
    // Handle custom exceptions
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Customer Not Found");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    
    //Actor Exceptions
    
    //Handle ActorAlreadyExistsException
    @ExceptionHandler(ActorAlreadyExistsException.class)
    public ResponseEntity<String> handleActorAlreadyExists(ActorAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
 
    //Handle ActorNotFoundException
    @ExceptionHandler(ActorNotFoundException.class)
    public ResponseEntity<String> handleActorNotFound(ActorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    //Handle InvalidActorDataException
    public ResponseEntity<String> handleInvalidActorData(InvalidActorDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    //Handle FilmNotFoundException
    @ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<String> handleFilmNotFound(FilmNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    //Handle FilmAssignmentException
    @ExceptionHandler(FilmAssignmentException.class)
    public ResponseEntity<String> handleFilmAssignment(FilmAssignmentException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
    
    
//    //Payment Exceptions
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePaymentNotFoundException(PaymentNotFoundException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("error", message);
        return new ResponseEntity<>(response, status);
    }
   
    


    

}
