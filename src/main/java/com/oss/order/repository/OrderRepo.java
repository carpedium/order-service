package com.oss.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.oss.order.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order>{

}
