package com.hcl.eCommerce.service.impl;

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

import com.hcl.ecommerce.Exception.ProductAlreadyExistsException;
import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.Exception.UserNotFoundException;
import com.hcl.ecommerce.dto.CartDto;
import com.hcl.ecommerce.dto.CartResponseDto;
import com.hcl.ecommerce.entity.Cart;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.CartRepository;
import com.hcl.ecommerce.service.ProductService;
import com.hcl.ecommerce.service.UserService;
import com.hcl.ecommerce.service.impl.CartServiceImpl;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {
	
	@Mock
	CartRepository cartRepository;
	
	@Mock
    ProductService productService;

	@Mock
    UserService userService;

	@InjectMocks
	CartServiceImpl cartServiceImpl;
	
	static Cart cart;
	
	static Product product;
	
	static User user;
	
	static List<Cart> carts;
	
	static CartResponseDto cartResponseDto;
	
	static CartResponseDto cartResponse;
	
	static List<CartResponseDto> cartResponseDtos;
	
	static CartDto cartDto;
	
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
		cart.setCartId(1L);
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(1);
		
		cartResponseDto=new CartResponseDto(cart);
		
		carts=new ArrayList<>();
		carts.add(cart);
		
		cartResponseDtos=new ArrayList<>();
		cartResponseDtos.add(cartResponseDto);
		
		cartDto = new CartDto(cartResponseDtos,45000.0);
		
	}
	
	@Test
	@DisplayName("Add Product: positive scenario")
	public void addProductTest() throws ProductNotFoundException {
		when(userService.getUser(anyLong())).thenReturn(user);
		when(productService.getProduct(anyLong())).thenReturn(product);
		when(cartRepository.save(any(Cart.class))).thenAnswer(i->{
			cart.setCartId(1L);
			return cart;
		});
		ResponseEntity<?> result =cartServiceImpl.addProduct(1,1,2);
		verify(cartRepository).save(any(Cart.class));
	    assertEquals("Product added to cart successfully", result.getBody());
	}
	
	@Test
	@DisplayName("Add Product: negative scenario")
	public void addProductTest1() throws ProductNotFoundException {
		when(userService.getUser(anyLong())).thenReturn(user);
		when(productService.getProduct(anyLong())).thenReturn(product);
		when(cartRepository.findByProductAndUser(any(Product.class),any(User.class))).thenReturn(cart);
		assertThrows(ProductAlreadyExistsException.class, ()->cartServiceImpl.addProduct(1,1,2));
	}
	
	@Test
	@DisplayName("List Cart Items: positive scenario")
	public void listCartItemsTest() {
		when(userService.getUser(1L)).thenReturn(user);
		when(cartRepository.findAllByUser(user)).thenReturn(carts);
		CartDto result =cartServiceImpl.listCartItems(1L);
		verify(cartRepository).findAllByUser(user);
		assertEquals(cartDto.getCartResponseDtos().size(),result.getCartResponseDtos().size());
		assertNotNull(result.getTotalCost());
	}
	
	@Test
	@DisplayName("Delete Cart Products: positive scenario")
	public void deleteCartProductsTest() {
		when(userService.getUser(anyLong())).thenReturn(user);
		when(cartRepository.deleteByUser(any(User.class))).thenReturn(null);
		cartServiceImpl.deleteCartProducts(anyLong());
		verify(cartRepository).deleteByUser(any(User.class));
	}
	
	@Test
	@DisplayName("Delete Cart Products: negative scenario")
	public void deleteCartProductsTest1() {
		when(userService.getUser(anyLong())).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () ->cartServiceImpl.deleteCartProducts(anyLong()));
	}
}

