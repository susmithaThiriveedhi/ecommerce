package com.hcl.ecommerce.dto;

import java.util.List;

public class CartDto {
	
	private List<CartResponseDto> cartResponseDtos;
    
	private double totalCost;
	
    public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartDto(List<CartResponseDto> cartResponseDtos, double totalCost) {
		super();
		this.cartResponseDtos = cartResponseDtos;
		this.totalCost = totalCost;
	}

	public List<CartResponseDto> getCartResponseDtos() {
		return cartResponseDtos;
	}

	public void setCartResponseDtos(List<CartResponseDto> CartResponseDtos) {
		this.cartResponseDtos = CartResponseDtos;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
    
}
