package com.oss.order.spec;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.oss.order.dto.OrderDto;
import com.oss.order.entity.Order;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductOrderSpec {

	public static Specification<Order> getSpec(OrderDto dto){
		
		Specification<Order> spec = new Specification<Order>() {
			
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new LinkedList<>();
				
				if(dto.getOrderType()!=null) {
					Predicate p = cb.like(root.get("orderType"), dto.getOrderType().toString());
					list.add(p);
				}
				
				if(dto.getOrderType()!=null) {
					Predicate p = cb.like(root.get("orderStatus"), dto.getStatus().toString());
					list.add(p);
				}
				
				return cb.and(list.toArray(new Predicate[0]));
			}
		};
		
		return spec;
	}
}
