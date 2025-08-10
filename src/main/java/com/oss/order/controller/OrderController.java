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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/hello")
	public ResponseEntity<String> getHello() {
		log.info("GET /orders/hello called");

		return ResponseEntity.ok("hello");
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<OrderDto> getProductDto(@PathVariable Long id) {
		log.info("GET /orders/{} - start", id);
		OrderDto dto = orderService.findById(id);
		log.info("GET /orders/{} - done (found={})", id, dto != null);

		return ResponseEntity.ok(dto);
	}

	@PostMapping("/get")
	public ResponseEntity<List<OrderDto>> getProducts(@RequestBody OrderDto dto,
			@PageableDefault(page = 0, size = 2, sort = "id", direction = ASC) Pageable pageable) {

		log.info("POST /orders/search page={} size={} sort={}", pageable.getPageNumber(), pageable.getPageSize(),
				pageable.getSort());
		log.debug("search payload: {}", dto);

		List<OrderDto> list = orderService.findByObjectExample(dto, pageable);
		log.info("search result count={}", list != null ? list.size() : 0);

		return ResponseEntity.ok(list);
	}

	@PostMapping("/create")
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto dto) {
		log.info("POST /orders/create - start");
		log.debug("create payload: {}", dto);

		OrderDto result = orderService.createOrder(dto);
		log.info("POST /orders/create - done id={}", result != null ? result.getId() : null);

		return ResponseEntity.ok(result);
	}
}
