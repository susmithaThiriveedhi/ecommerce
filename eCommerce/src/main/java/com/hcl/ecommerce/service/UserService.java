package com.hcl.ecommerce.service;

import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.entity.User;


public interface UserService {

	ResponseEntity<String> login(String userName, String password);

	User getUser(long userId);

}
