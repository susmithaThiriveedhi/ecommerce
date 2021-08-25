package com.hcl.ecommerce.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> getProducts(@Valid @NotEmpty @RequestParam String category) throws ProductNotFoundException{	
		try {
			return new ResponseEntity<>(productService.getProducts(category),HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			throw e;
		}		
	}
	
	@GetMapping("/ProductName")
	public ResponseEntity<?> getProductsByName(@Valid @NotEmpty @RequestParam String productName) throws ProductNotFoundException{	
		try {
			return new ResponseEntity<>(productService.getProductsByName(productName),HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			throw e;
		}		
	}
}
