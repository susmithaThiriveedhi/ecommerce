package com.hcl.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.CredentialsDto;
import com.hcl.ecommerce.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
	
	@Autowired 
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody  CredentialsDto credentials) {
		return userService.login(credentials.getUserName(), credentials.getPassword());
	}
}
