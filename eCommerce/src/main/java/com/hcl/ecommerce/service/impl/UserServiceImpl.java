package com.hcl.ecommerce.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.Exception.InvalidCredentialsException;
import com.hcl.ecommerce.Exception.UserNotFoundException;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.UserRepository;
import com.hcl.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseEntity<String> login(String userName, String password) {
		User user = userRepository.findByUserNameAndPassword(userName, password);
		if (user != null) {
			return new ResponseEntity<>("Login successfull", HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Credentials..!!Please Verify your userName and password.");
	}

	@Override
	public User getUser(long userId) {
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			throw new UserNotFoundException("User Doesn't exists...!!Please enter correct user id");
		}
	}
	
}
