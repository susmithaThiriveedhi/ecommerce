package com.hcl.ecommerce.Exception;

public class OrderNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String message;

	public OrderNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public OrderNotFoundException() {
		super();
	}
}
