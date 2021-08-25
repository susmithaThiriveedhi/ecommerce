package com.hcl.eCommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.Exception.InvalidCredentialsException;
import com.hcl.ecommerce.controller.UserController;
import com.hcl.ecommerce.dto.CredentialsDto;
import com.hcl.ecommerce.service.UserService;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	static CredentialsDto credentialsDto;
	
	@BeforeAll
	public static void setUp() {
		credentialsDto = new CredentialsDto();
		credentialsDto.setUserName("susmitha");
		credentialsDto.setPassword("Susmitha@31");
	}
	
	@Test
	@DisplayName("Login Function: Positive Scenario")
	public void loginTest() {
		when(userService.login(anyString(), anyString())).thenReturn(
				new ResponseEntity<>("Login successfull", HttpStatus.OK));
		
		ResponseEntity<String> result = userController.login(credentialsDto);
		verify(userService).login("susmitha", "Susmitha@31");
		assertEquals("Login successfull", result.getBody());
	}
	
	@Test
	@DisplayName("Login Function: Negative Scenario")
	public void loginTest2() {
		when(userService.login(anyString(), anyString())).thenThrow(InvalidCredentialsException.class);
		assertThrows(InvalidCredentialsException.class, ()->userController.login(credentialsDto));
	}
}
