package com.oss.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class OrderDto {
	private Long id;
	private String name;

	@Enumerated(EnumType.STRING)
	private OrderType orderType;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

}
