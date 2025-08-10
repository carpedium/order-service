package com.oss.order.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oss.order.clients.InventoryClient;
import com.oss.order.dto.OrderStatus;
import com.oss.order.dto.OrderType;
import com.oss.order.dto.OrderDto;
import com.oss.order.dto.inventory.DeviceInstanceDTO;
import com.oss.order.entity.Order;
import com.oss.order.mapper.ProductOrderMapper;
import com.oss.order.repository.OrderRepo;
import com.oss.order.spec.ProductOrderSpec;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

	@Autowired
	OrderRepo repo;
	
	@Autowired
	InventoryClient iClient;
	
	@Autowired
	private ProductOrderMapper modelMapper; // Or convert manually

	public OrderDto findById(Long id) {
		Optional<Order> p = repo.findById(id);
		ObjectMapper mapper=new ObjectMapper();
		OrderDto dto = mapper.convertValue( (p.isEmpty() ? null : p.get()), OrderDto.class);
		return dto;
	}

	public List<OrderDto> findByObjectExample(OrderDto dto, Pageable pageable) {
		
		Order product = modelMapper.toEntity(dto);
		ExampleMatcher matcher = ExampleMatcher.matchingAll()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.EXACT); 
		
		Example<Order> example=Example.of(product, matcher);
		Page<Order> list  = repo.findAll(example, pageable);		
		
		
		return list.getContent().stream().map(t -> modelMapper.toDto(t)).collect(Collectors.toList());
	}
	
	public List<OrderDto> findBySpec(OrderDto dto, Pageable pageable) {
		
		Specification<Order> spec = ProductOrderSpec.getSpec(dto);
		
		Page<Order> page = repo.findAll(spec, pageable);
		
		return page.getContent().stream().map(x->modelMapper.toDto(x)).collect(Collectors.toList());
			
	}

	public OrderDto createOrder(OrderDto dto) {
		
		log.info("1  : dto "+dto);

		Order po=modelMapper.toEntity(dto);
		po.setStatus(OrderStatus.COMPLETED);
		log.info("2  : po "+po);
		
		po = repo.save(po);
		
		log.info("3  : po "+po);
		
		DeviceInstanceDTO deviceDto = DeviceInstanceDTO.builder()
				.status("AVAILABLE")
				.deviceType( dto.getOrderType().equals(OrderType.MOBILE)?  "MOBILE" : "LANDLINE")
				.build();

		log.info("4  : deviceDto "+deviceDto);

		deviceDto = iClient.findTopByExample(deviceDto).getBody();
		
		log.info("5  : deviceDto "+deviceDto);

		deviceDto.setUsedForId(po.getId());
		deviceDto.setStatus("ACTIVE");
		
		log.info("6  : deviceDto "+deviceDto);

		
		iClient.update(deviceDto);
		log.info("7  : po "+po);
		
		iClient.probe();
		
		log.info("8 : probe ");


		return modelMapper.toDto(po);
	}
}
