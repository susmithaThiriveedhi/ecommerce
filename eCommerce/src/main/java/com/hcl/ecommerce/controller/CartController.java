package com.hcl.ecommerce.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.service.CartService;

@RestController
@RequestMapping("/carts")
@Validated
public class CartController {
	
	@Autowired
	CartService cartService;
    
    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @Min(1) @RequestParam long userId,@Valid @Min(1) @RequestParam long productId,@Valid @Min(1) @RequestParam int quantity)throws ProductNotFoundException{
		try {
			return cartService.addProduct(userId,productId,quantity);
		}
		catch(ProductNotFoundException e){
			throw e;
		}
    }
}
