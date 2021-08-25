package com.hcl.ecommerce.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.service.OrderService;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping
	public ResponseEntity<?> buyProducts(@Valid @Min(1) @RequestParam long userId){
		return orderService.buyProducts(userId);
	}
	
	@GetMapping()
    public ResponseEntity<?> getAllOrders(@Valid @Min(1) @RequestParam long userId){
       return orderService.getAllOrders(userId);
    }
}
