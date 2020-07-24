package com.mindtree.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.order.entity.Order;

public interface IOrderRepository extends JpaRepository<Order, Long>{

}
