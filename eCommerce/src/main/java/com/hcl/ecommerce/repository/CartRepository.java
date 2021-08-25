package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Cart;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

	//Cart findByProduct(Product product);

	Cart findByProductAndUser(Product product, User user);

	List<Cart> findAllByUser(User user);

	List<Cart> deleteByUser(User user);

}
