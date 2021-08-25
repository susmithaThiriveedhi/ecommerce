package com.hcl.ecommerce.Exception;

public class CartIsEmptyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String message;

	public CartIsEmptyException(String message) {
		super(message);
		this.message = message;
	}

	public CartIsEmptyException() {
		super();
	}
}
