package com.hcl.ecommerce.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.Exception.CartIsEmptyException;
import com.hcl.ecommerce.Exception.OrderNotFoundException;
import com.hcl.ecommerce.dto.CartDto;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.OrderRepository;
import com.hcl.ecommerce.service.CartService;
import com.hcl.ecommerce.service.OrderService;
import com.hcl.ecommerce.service.UserService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;
    
    @Autowired
    UserService userService;
     
	@Override
	@Transactional
	public ResponseEntity<?> buyProducts(long userId) {
		CartDto cartDto = cartService.listCartItems(userId);
		
		User user=userService.getUser(userId);
		Order order=new Order();
		order.setOrderedDate(LocalDate.now());
		order.setUser(user);
		order.setTotalPrice(cartDto.getTotalCost());
		
		if(cartDto.getCartResponseDtos().size()!=0) {
			orderRepository.save(order);
			cartService.deleteCartProducts(userId);
			return new ResponseEntity<>("Order Placed Successfully.",HttpStatus.OK);
		}
		else {
			throw new CartIsEmptyException("Couldn't place order...!!, your cart is empty");
		}
	}

	@Override
	public ResponseEntity<?> getAllOrders(long userId) {
		User user=userService.getUser(userId);
		List<Order> orderList = orderRepository.findAllByUserOrderByOrderedDateDesc(user);
		if(orderList.size()!=0) {
		return new ResponseEntity<>(orderList,HttpStatus.OK);
		}
		throw new OrderNotFoundException("Order Not Found...!!");
	}

}
