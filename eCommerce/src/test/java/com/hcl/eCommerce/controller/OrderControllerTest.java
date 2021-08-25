package com.hcl.eCommerce.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.Exception.CartIsEmptyException;
import com.hcl.ecommerce.Exception.OrderNotFoundException;
import com.hcl.ecommerce.controller.OrderController;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	@Mock
	OrderService orderService;
	
	@InjectMocks
	OrderController orderController;
	
	static Order order;
	
	static User user;
	
	static List<Order> orders;
	
	@BeforeAll
	public static void setUp() {
		user = new User();
		user.setUserId(1L);
		user.setUserName("Susmitha");
		user.setPassword("Susmitha$24");
		user.setMobileNo("9875676786");
		user.setMailId("susmitha@gmail.com");
		
		order =new Order();
		order.setOrderedDate(LocalDate.now());
		order.setTotalPrice(7800.0);
		order.setUser(user);
		
		orders=new ArrayList<>();
		orders.add(order);
	}
	
	@Test
	@DisplayName("Buy Products: Positive Scenario")
	public void buyProductsTest() {
		when(orderService.buyProducts(anyLong())).thenAnswer(i->{
			order.setOrderId(1L);
			return new ResponseEntity<>("Order Placed Successfully.", HttpStatus.CREATED);
		});
		ResponseEntity<?> result =orderController.buyProducts(anyLong());
		verify(orderService).buyProducts(anyLong());
		assertEquals("Order Placed Successfully.", result.getBody());
	}
	
	@Test
	@DisplayName("Buy Products: Negative Scenario")
	public void buyProductsTest1() {
		when(orderService.buyProducts(anyLong())).thenThrow(CartIsEmptyException.class);
		assertThrows(CartIsEmptyException.class,()->orderController.buyProducts(anyLong()));
	}
	
	@Test
	@DisplayName("Get All Products: Positive Scenario")
	public void getAllOrdersTest() {
		when(orderService.getAllOrders(anyLong())).thenAnswer(i->{
			return new ResponseEntity<>(orders,HttpStatus.OK);
		});
		ResponseEntity<?> result =orderController.getAllOrders(anyLong());
		verify(orderService).getAllOrders(anyLong());
		assertEquals(orders, result.getBody());
	}
	
	@Test
	@DisplayName("Get All Products: Negative Scenario")
	public void getAllOrdersTest1() {
		when(orderService.getAllOrders(anyLong())).thenThrow(OrderNotFoundException.class);
		assertThrows(OrderNotFoundException.class,()->orderController.getAllOrders(anyLong()));
	}
}
