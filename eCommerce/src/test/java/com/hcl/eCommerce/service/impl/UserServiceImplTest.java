package com.hcl.eCommerce.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.Exception.InvalidCredentialsException;
import com.hcl.ecommerce.Exception.UserNotFoundException;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.UserRepository;
import com.hcl.ecommerce.service.impl.UserServiceImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	static User user;
	
	static Optional<User> options;
	
	@BeforeAll
	public static void setUp() {
		
		user = new User();
		user.setUserName("Susmitha");
		user.setPassword("Susmitha$24");
		user.setMobileNo("9875676786");
		user.setMailId("susmitha@gmail.com");
		
		options=Optional.of(user);
	}
	@Test
	@DisplayName("Login Function : positive scenario")
	public void loginTest() {
		when(userRepository.findByUserNameAndPassword(anyString(), anyString())).thenReturn(user);
		
		ResponseEntity<String> result = userServiceImpl.login(anyString(), anyString());
		verify(userRepository).findByUserNameAndPassword(anyString(), anyString());
		
	    assertEquals("Login successfull", result.getBody());
	}
	
	@Test
	@DisplayName("Login Function : negative scenario")
	public void loginTest1() {
		when(userRepository.findByUserNameAndPassword(anyString(), anyString())).thenReturn(null);
		
		assertThrows(InvalidCredentialsException.class, () ->userServiceImpl.login(anyString(), anyString()));	
	}
	
	@Test
	@DisplayName("Get User : positive scenario")
	public void getUserTest() {
		when(userRepository.findById(anyLong())).thenReturn(options);
		
		User result = userServiceImpl.getUser(anyLong());
		verify(userRepository).findById(anyLong());
		
		assertEquals(options.get(),result);
	}
	
	@Test
	@DisplayName("Get User : negative scenario")
	public void getUserTest1() {
		when(userRepository.findById(anyLong())).thenThrow(UserNotFoundException.class);
		
		assertThrows(UserNotFoundException.class, () ->userServiceImpl.getUser(anyLong()));
	}
}
