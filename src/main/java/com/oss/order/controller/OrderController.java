package com.oss.order.controller;

import static org.springframework.data.domain.Sort.Direction.ASC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.order.dto.OrderDto;
import com.oss.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<OrderDto> getProductDto  ( @PathVariable Integer id){
		OrderDto dto = orderService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/get")
	public ResponseEntity<List<OrderDto>> getProducts  ( 
			@RequestBody OrderDto dto,
			@PageableDefault(page = 0, size=2, sort = "id", direction = ASC) Pageable pageable
			){
		List<OrderDto> list = orderService.findByObjectExample(dto, pageable);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/create")
	public ResponseEntity<OrderDto> createOrder  (@RequestBody OrderDto dto){

		OrderDto result = orderService.createOrder(dto);
		
		return ResponseEntity.ok(result);
	}
}
