package com.oss.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import static org.springframework.data.domain.Sort.Direction.ASC;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oss.order_service.dto.ProductOrderDto;
import com.oss.order_service.service.ProductOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private ProductOrderService orderService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ProductOrderDto> getProductDto  ( @PathVariable Integer id){
		ProductOrderDto dto = orderService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<ProductOrderDto>> getProducts  ( 
			@RequestBody ProductOrderDto dto,
			@PageableDefault(page = 0, size=2, sort = "id", direction = ASC) Pageable pageable
			){
		List<ProductOrderDto> list = orderService.findByObjectExample(dto, pageable);
		return ResponseEntity.ok(list);
	}
}
