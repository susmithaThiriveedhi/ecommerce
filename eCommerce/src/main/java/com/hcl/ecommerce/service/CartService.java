package com.hcl.ecommerce.service;

import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.dto.CartDto;

public interface CartService {

	ResponseEntity<?> addProduct(long userId,long productId,int quantity)throws ProductNotFoundException;

	CartDto listCartItems(long userId);

	void deleteCartProducts(long userId);

}
