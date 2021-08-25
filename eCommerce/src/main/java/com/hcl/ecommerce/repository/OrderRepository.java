package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

	List<Order> findAllByUserOrderByOrderedDateDesc(User user);

}
