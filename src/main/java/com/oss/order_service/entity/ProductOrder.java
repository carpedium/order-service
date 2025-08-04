package com.oss.order_service.entity;

import com.oss.order_service.dto.OrderStatus;
import com.oss.order_service.dto.OrderType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "orders")
public class ProductOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	@Enumerated(EnumType.STRING)
	private OrderType orderType;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

}
