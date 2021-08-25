package com.hcl.ecommerce.dto;

import com.hcl.ecommerce.entity.Cart;
import com.hcl.ecommerce.entity.Product;

public class CartResponseDto {
	
	private long cartId;
	
    private long userId;
    
    private int quantity;
    
    private Product product;

    public CartResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartResponseDto(Cart cart) {
        this.setCartId(cart.getCartId());
        this.setUserId(cart.getUser().getUserId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
}
