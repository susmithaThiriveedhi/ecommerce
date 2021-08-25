package com.hcl.ecommerce.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.Exception.ProductAlreadyExistsException;
import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.Exception.UserNotFoundException;
import com.hcl.ecommerce.dto.CartDto;
import com.hcl.ecommerce.dto.CartResponseDto;
import com.hcl.ecommerce.entity.Cart;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.CartRepository;
import com.hcl.ecommerce.service.CartService;
import com.hcl.ecommerce.service.ProductService;
import com.hcl.ecommerce.service.UserService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
    ProductService productService;

    @Autowired
    UserService userService;

	@Override
	public ResponseEntity<?> addProduct(long userId, long productId, int quantity) throws ProductNotFoundException {
		Cart cart=new Cart();
		User user=userService.getUser(userId); 
		Product product=productService.getProduct(productId);
		cart.setProduct(product);
		cart.setUser(user); 
		cart.setQuantity(quantity);
		Cart cartExisting=cartRepository.findByProductAndUser(product,user);
		if(cartExisting!=null) {
			throw new ProductAlreadyExistsException("Product already exists in cart...!!");		
		} 
		cartRepository.save(cart);
		return new ResponseEntity<>("Product added to cart successfully", HttpStatus.CREATED);
	}
	
	@Override
	public CartDto listCartItems(long userId) {
		User user=userService.getUser(userId);
	    List<Cart> cartList = cartRepository.findAllByUser(user);
	    List<CartResponseDto> cartResponseDtos = new ArrayList<>();
	    for (Cart cart:cartList){
	       CartResponseDto cartResponse = new CartResponseDto(cart);
	       cartResponseDtos.add(cartResponse);
	    }
	    double totalCost = 0;
	    for (CartResponseDto cartResponseDto :cartResponseDtos){
	        totalCost += (cartResponseDto.getProduct().getPrice()* cartResponseDto.getQuantity());
        }
	    CartDto cartDto = new CartDto(cartResponseDtos,totalCost);
	        
	    return cartDto;
	}

	@Override
	public void deleteCartProducts(long userId) {
		User user=userService.getUser(userId);
		if(user!=null) {
		cartRepository.deleteByUser(user);
		}
		else {
			throw new UserNotFoundException("User Doesn't exists...!!");
		}
	}
}
