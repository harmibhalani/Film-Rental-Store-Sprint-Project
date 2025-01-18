package com.cg.exception;
 
public class PaymentNotFoundException extends RuntimeException {
	public PaymentNotFoundException(String message) {
		super(message);
	}
 
	public PaymentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
 
//    public PaymentNotFoundException(Throwable cause) {
//        super(cause);
//    }
}