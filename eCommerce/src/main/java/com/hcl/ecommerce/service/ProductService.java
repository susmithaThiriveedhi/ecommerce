package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.Exception.ProductNotFoundException;
import com.hcl.ecommerce.dto.ProductResponseDto;
import com.hcl.ecommerce.entity.Product;

public interface ProductService {

	List<ProductResponseDto> getProducts(String category)throws ProductNotFoundException;

	Product getProduct(long productId)throws ProductNotFoundException;

	List<ProductResponseDto> getProductsByName(String productName)throws ProductNotFoundException;

}
