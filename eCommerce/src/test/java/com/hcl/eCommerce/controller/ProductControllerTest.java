package com.hcl.eCommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.controller.ProductController;
import com.hcl.ecommerce.dto.ProductResponseDto;
import com.hcl.ecommerce.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
	@Mock
	ProductService productService;
	
	@InjectMocks
	ProductController productController;
	
	static ProductResponseDto productResponseDto;
	
	static List<ProductResponseDto> productResponseDtos;
	
	@BeforeAll
	public static void setUp() {
		productResponseDto=new ProductResponseDto();
		productResponseDto.setProductId(1L);
		productResponseDto.setProductName("laptop");
		productResponseDto.setPrice(45000);
		
		productResponseDtos=new ArrayList<>();
		productResponseDtos.add(productResponseDto);
	}
	
	@Test
	@DisplayName("Get Products: Positive Scenario")
	public void getProductsTest() throws ProductNotFoundException{
		when(productService.getProducts(anyString())).thenReturn(productResponseDtos);
		ResponseEntity<?> result=productController.getProducts(anyString());
		verify(productService).getProducts(anyString());
		assertEquals(productResponseDtos,result.getBody());
	}
	
	@Test
	@DisplayName("Get Products: Negative Scenario")
	public void getProductsTest1() throws ProductNotFoundException{
		when(productService.getProducts(anyString())).thenThrow(ProductNotFoundException.class);
		assertThrows(ProductNotFoundException.class,()->productController.getProducts(anyString()));
	}
	
	@Test
	@DisplayName("Get Products By Name: Positive Scenario")
	public void getProductsByNameTest() throws ProductNotFoundException{
		when(productService.getProductsByName(anyString())).thenReturn(productResponseDtos);
		ResponseEntity<?> result=productController.getProductsByName(anyString());
		verify(productService).getProductsByName(anyString());
		assertEquals(productResponseDtos,result.getBody());
	}
	
	@Test
	@DisplayName("Get Products: Negative Scenario")
	public void getProductsByNameTest1() throws ProductNotFoundException{
		when(productService.getProductsByName(anyString())).thenThrow(ProductNotFoundException.class);
		assertThrows(ProductNotFoundException.class,()->productController.getProductsByName(anyString()));
	}
}
