package com.hcl.ecommerce.service;

import org.springframework.http.ResponseEntity;

public interface OrderService {

	ResponseEntity<?> buyProducts(long userId);

	ResponseEntity<?> getAllOrders(long userId);

}
