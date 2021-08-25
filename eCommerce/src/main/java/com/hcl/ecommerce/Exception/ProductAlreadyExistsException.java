package com.hcl.ecommerce.Exception;

public class ProductAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public String message;

	public ProductAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public ProductAlreadyExistsException() {
		super();
	}
}
