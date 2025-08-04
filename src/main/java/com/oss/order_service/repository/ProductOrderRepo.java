package com.oss.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.oss.order_service.entity.ProductOrder;

@Repository
public interface ProductOrderRepo extends JpaRepository<ProductOrder, Integer>, JpaSpecificationExecutor<ProductOrder>{

}
