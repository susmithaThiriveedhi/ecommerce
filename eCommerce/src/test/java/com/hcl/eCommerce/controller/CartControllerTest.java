package com.hcl.eCommerce.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.controller.CartController;
import com.hcl.ecommerce.entity.Cart;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.CartService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
	@Mock
	CartService cartService;
	
	@InjectMocks
	CartController cartController;
	
	static User user;
	
	static Product product;
	
	static Cart cart;
	
	@BeforeAll
	public static void setUp() {
		user = new User();
		user.setUserId(1L);
		user.setUserName("Susmitha");
		user.setPassword("Susmitha$24");
		user.setMobileNo("9875676786");
		user.setMailId("susmitha@gmail.com");
		
		product=new Product();
		product.setProductId(1L);
		product.setProductName("laptop");
		product.setPrice(45000);
		product.setCategory("Electronics");
		
		cart=new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(1);
	}
	
	@Test
	@DisplayName("Add Product: Positive Scenario")
	public void addProduct() throws ProductNotFoundException{
		when(cartService.addProduct(anyLong(),anyLong(),anyInt())).thenAnswer(i->{
			cart.setCartId(1L);
			return new ResponseEntity<>("Product added to cart successfully", HttpStatus.CREATED);
		});
		
		ResponseEntity<?> result =cartController.addProduct(anyLong(),anyLong(),anyInt());
		verify(cartService).addProduct(anyLong(),anyLong(),anyInt());
		assertEquals("Product added to cart successfully", result.getBody());
	}
	
	@Test
	@DisplayName("Add Product: negative Scenario")
	public void addProduct1() throws ProductNotFoundException{
		when(cartService.addProduct(anyLong(),anyLong(),anyInt())).thenThrow(ProductNotFoundException.class);
		assertThrows(ProductNotFoundException.class,()-> cartController.addProduct(anyLong(),anyLong(),anyInt()));
	}
}
