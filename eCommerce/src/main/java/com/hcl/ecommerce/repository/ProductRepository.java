package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

	//List<Product> findByCategoryLike(String category);

	List<Product> findByCategoryStartingWith(String category);

	Product findByProductName(String productName);

	List<Product> findByProductId(Long productId);

	List<Product> findByProductNameStartingWith(String productName);

}
