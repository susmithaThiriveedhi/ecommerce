package com.hcl.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.dto.ProductResponseDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;
		
	@Override
	public List<ProductResponseDto> getProducts(String category) throws ProductNotFoundException{
		List<Product> products=productRepository.findByCategoryStartingWith(category);
		List<ProductResponseDto> productResponseDtos=new ArrayList<>();
		for(Product product: products) {
			ProductResponseDto productResponseDto=new ProductResponseDto();
			BeanUtils.copyProperties(product,productResponseDto);
			productResponseDtos.add(productResponseDto);
		}
		if(productResponseDtos.size()!=0) {
			return productResponseDtos;
		//	return new ResponseEntity<>(productResponseDtos,HttpStatus.OK);
		}
		else{
			throw new ProductNotFoundException("Products Not Found...!!Please search in another category");
		}
	}

	@Override
	public Product getProduct(long productId) throws ProductNotFoundException {
		Optional<Product> product=productRepository.findById(productId);
		if(product.isPresent()) {
			return product.get();
		}
		else {
			throw new ProductNotFoundException("Products Not Found...!!");
		}
	}

	@Override
	public List<ProductResponseDto> getProductsByName(String productName) throws ProductNotFoundException {
		List<Product> products=productRepository.findByProductNameStartingWith(productName);
		List<ProductResponseDto> productResponseDtos=new ArrayList<>();
		for(Product product: products) {
			ProductResponseDto productResponseDto=new ProductResponseDto();
			BeanUtils.copyProperties(product,productResponseDto);
			productResponseDtos.add(productResponseDto);
		}
		if(productResponseDtos.size()!=0) {
			return productResponseDtos;
		//	return new ResponseEntity<>(productResponseDtos,HttpStatus.OK);
		}
		else{
			throw new ProductNotFoundException("Products Not Found...!!Please search in another category");
		}
	}

}
