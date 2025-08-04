package com.oss.order_service.spec;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.oss.order_service.dto.ProductOrderDto;
import com.oss.order_service.entity.ProductOrder;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductOrderSpec {

	public static Specification<ProductOrder> getSpec(ProductOrderDto dto){
		
		Specification<ProductOrder> spec = new Specification<ProductOrder>() {
			
			@Override
			public Predicate toPredicate(Root<ProductOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
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
