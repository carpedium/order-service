package com.oss.order.mapper;

import org.springframework.stereotype.Component;

import com.oss.order.dto.OrderDto;
import com.oss.order.entity.Order;

/*@Mapper(componentModel = "spring") // Enables Spring to detect and inject this mapper as a bean
public interface OrderMapper {

	OrderDto toDTO(Order entity); 
	Order toEntity(OrderDto entity);
}
*/

@Component
public class ProductOrderMapper {

    public OrderDto toDto(Order entity) {
        if (entity == null) return null;

        return OrderDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .orderType(entity.getOrderType())
                .status(entity.getStatus())
                .build();
    }

    public Order toEntity(OrderDto dto) {
        if (dto == null) return null;

        return Order.builder()
                .id(dto.getId())
                .name(dto.getName())
                .orderType(dto.getOrderType())
                .status(dto.getStatus())
                .build();
    }
}