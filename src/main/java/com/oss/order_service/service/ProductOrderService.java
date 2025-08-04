package com.oss.order_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oss.order_service.dto.ProductOrderDto;
import com.oss.order_service.entity.ProductOrder;
import com.oss.order_service.repository.ProductOrderRepo;
import com.oss.order_service.spec.ProductOrderSpec;

@Service
public class ProductOrderService {

	@Autowired
	ProductOrderRepo repo;
	
	@Autowired
	private ModelMapper modelMapper; // Or convert manually

	public ProductOrderDto findById(Integer id) {
		Optional<ProductOrder> p = repo.findById(id);
		ObjectMapper mapper=new ObjectMapper();
		ProductOrderDto dto = mapper.convertValue( (p.isEmpty() ? null : p.get()), ProductOrderDto.class);
		return dto;
	}

	public List<ProductOrderDto> findByObjectExample(ProductOrderDto dto, Pageable pageable) {
		
		ProductOrder product = modelMapper.map(dto, ProductOrder.class);
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.EXACT); 
		
		Example<ProductOrder> example=Example.of(product, matcher);
		Page<ProductOrder> list  = repo.findAll(example, pageable);		
		
		
		return list.getContent().stream().map(t -> modelMapper.map(t, ProductOrderDto.class)).collect(Collectors.toList());
	}
	
	public List<ProductOrderDto> findBySpec(ProductOrderDto dto, Pageable pageable) {
		
		Specification<ProductOrder> spec = ProductOrderSpec.getSpec(dto);
		
		Page<ProductOrder> page = repo.findAll(spec, pageable);
		
		return page.getContent().stream().map(x-> modelMapper.map(x, ProductOrderDto.class)).collect(Collectors.toList());
			
	}
}
