package com.oss.order_service.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductOrderDto {
	private String name;

	@Enumerated(EnumType.STRING)
	private OrderType orderType;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

}
