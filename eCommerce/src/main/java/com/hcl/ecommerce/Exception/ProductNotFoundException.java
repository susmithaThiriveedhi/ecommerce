package com.hcl.ecommerce.Exception;

public class ProductNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public String message;

	public ProductNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public ProductNotFoundException() {
		super();
	}

}
