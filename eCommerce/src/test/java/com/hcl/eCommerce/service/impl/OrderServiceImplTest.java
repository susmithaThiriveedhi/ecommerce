package com.hcl.eCommerce.service.impl;

import java.time.LocalDate;
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

import com.hcl.ecommerce.Exception.CartIsEmptyException;
import com.hcl.ecommerce.Exception.OrderNotFoundException;
import com.hcl.ecommerce.dto.CartDto;
import com.hcl.ecommerce.dto.CartResponseDto;
import com.hcl.ecommerce.entity.Cart;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.OrderRepository;
import com.hcl.ecommerce.service.CartService;
import com.hcl.ecommerce.service.UserService;
import com.hcl.ecommerce.service.impl.OrderServiceImpl;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

	@Mock
	OrderRepository orderRepository;
	
	@Mock
	CartService cartService;
	
	@Mock
    UserService userService;

	@InjectMocks
	OrderServiceImpl orderServiceImpl;
	
	static Order order;
	
	static User user;
	
	static Product product;
	
	static Cart cart;
	
	static CartResponseDto cartResponseDto;
	
	static List<CartResponseDto> cartResponseDtos;
	
	static CartDto cartDto;
	
	static List<Order> orders;
	
	@BeforeAll
	public static void setUp() {
		user = new User();
		user.setUserId(1L);
		user.setUserName("Susmitha");
		user.setPassword("Susmitha$24");
		user.setMobileNo("9875676786");
		user.setMailId("susmitha@gmail.com");
		
		order=new Order();
		order.setUser(user);
		order.setOrderedDate(LocalDate.now());
		order.setTotalPrice(84374.0);
		
		product=new Product();
		product.setProductId(1L);
		product.setProductName("laptop");
		product.setPrice(45000);
		product.setCategory("Electronics");
		
		cart=new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(1);
		
		cartResponseDto=new CartResponseDto();
		cartResponseDto.setCartId(1L);
		cartResponseDto.setUserId(1L);
		cartResponseDto.setQuantity(1);
		cartResponseDto.setProduct(product);
		
		cartResponseDtos=new ArrayList<>();
		cartResponseDtos.add(cartResponseDto);
		
		cartDto = new CartDto(cartResponseDtos,74356.0);
		
		orders=new ArrayList<>();
		orders.add(order);
	}
	
	@Test
	@DisplayName("Buy Products: positive scenario")
	public void buyProductsTest() {
		when(cartService.listCartItems(anyLong())).thenReturn(cartDto);
		when(userService.getUser(anyLong())).thenReturn(user);
		when(orderRepository.save(any(Order.class))).thenReturn(order);
		doNothing().when(cartService).deleteCartProducts(anyLong());
		ResponseEntity<?> result=orderServiceImpl.buyProducts(1L);
		verify(orderRepository).save(any(Order.class));
		assertEquals("Order Placed Successfully.",result.getBody());
	}
	
	@Test
	@DisplayName("Buy Products: negative scenario")
	public void buyProductsTest1() {
		when(cartService.listCartItems(anyLong())).thenThrow(CartIsEmptyException.class);
		assertThrows(CartIsEmptyException.class,()->orderServiceImpl.buyProducts(1L));
	}
	
	@Test
	@DisplayName("Get All Orders: positive scenario")
	public void getAllOrdersTest() {
		when(userService.getUser(anyLong())).thenReturn(user);
		when(orderRepository.findAllByUserOrderByOrderedDateDesc(user)).thenReturn(orders);
		ResponseEntity<?> result=orderServiceImpl.getAllOrders(anyLong());
		verify(orderRepository).findAllByUserOrderByOrderedDateDesc(user);
		assertEquals(orders,result.getBody());
	}
	
	@Test
	@DisplayName("Get All Orders: negative scenario")
	public void getAllOrdersTest1() {
		when(userService.getUser(anyLong())).thenReturn(user);
		when(orderRepository.findAllByUserOrderByOrderedDateDesc(user)).thenThrow(OrderNotFoundException.class);
		assertThrows(OrderNotFoundException.class,()->orderServiceImpl.getAllOrders(anyLong()));
	}
	
}
