package com.hcl.eCommerce.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.dto.ProductResponseDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.service.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	static Product product;
	
	static Optional<Product> options;
	
	static ProductResponseDto productResponseDto;
	
	static List<Product> products;
	
	static List<ProductResponseDto> productResponseDtos;
	
	@BeforeAll
	public static void setUp() {
		product=new Product();
		product.setProductId(1L);
		product.setProductName("laptop");
		product.setPrice(45000);
		product.setCategory("Electronics");
		
		productResponseDto=new ProductResponseDto();
		productResponseDto.setProductId(1L);
		productResponseDto.setProductName("laptop");
		productResponseDto.setPrice(45000);
		
		products=new ArrayList<>();
		products.add(product);
		
		productResponseDtos=new ArrayList<>();
		productResponseDtos.add(productResponseDto);
		
		options=Optional.of(product);
	}
	
	@Test
	@DisplayName("Get Products : positive scenario")
	public void getProductsTest() throws ProductNotFoundException {
		when(productRepository.findByCategoryStartingWith(anyString())).thenReturn(products);
		List<ProductResponseDto> productResponseDtos1=productServiceImpl.getProducts(anyString());
		verify(productRepository).findByCategoryStartingWith(anyString());
    	assertEquals(products.size(),productResponseDtos1.size());
	}
	
	@Test
	@DisplayName("Get Product : positive scenario")
	public void getProductTest() throws ProductNotFoundException{
		when(productRepository.findById(anyLong())).thenReturn(options);
		
		Product result=productServiceImpl.getProduct(anyLong());
		verify(productRepository).findById(anyLong());
		
		assertEquals(options.get(),result);
	}
	
	@Test
	@DisplayName("Get Products By Name: positive scenario")
	public void getProductsByNameTest() throws ProductNotFoundException {
		when(productRepository.findByProductNameStartingWith(anyString())).thenReturn(products);
		List<ProductResponseDto> productResponseDtos1=productServiceImpl.getProductsByName(anyString());
		verify(productRepository).findByProductNameStartingWith(anyString());
    	assertEquals(products.size(),productResponseDtos1.size());
	}
	
	
//	@Test
//	@DisplayName("Get Product : negative scenario")
//	public void getProductTest1() throws ProductNotFoundException{
//		//when(productRepository.findById(anyLong())).thenReturn(null);
//		doThrow(ProductNotFoundException.class).when(productRepository).findById(1L);
//		assertThrows(ProductNotFoundException.class,()->productServiceImpl.getProduct(anyLong()));
//	}
	
//	@Test
//	@DisplayName("Get Products : negative scenario")
//	public void getProductsTest1() throws ProductNotFoundException {
//		when(productRepository.findByCategoryStartingWith(anyString())).thenThrow(ProductNotFoundException.class);
//		List<ProductResponseDto> productResponseDtos1=productServiceImpl.getProducts(anyString());
//		assertNull(productResponseDtos1);
//		//assertThrows(ProductNotFoundException.class,()->productServiceImpl.getProducts(anyString()));
//	}
}
