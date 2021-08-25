package com.hcl.ecommerce.Exception;

public class InvalidCredentialsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	String message;

	public InvalidCredentialsException(String message) {
		super(message);
		this.message = message;
	}

	public InvalidCredentialsException() {
		super();
	}
}
