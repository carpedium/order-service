package com.oss.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oss.order.dto.inventory.DeviceInstanceDTO;

import jakarta.servlet.http.HttpServletRequest;

@FeignClient(name = "inventory-service", url = "http://localhost:8081", path = "/inventory")
public interface InventoryClient {

	@PostMapping("/getsingle")
	public ResponseEntity<DeviceInstanceDTO> findTopByExample(@RequestBody DeviceInstanceDTO example);

	@PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody DeviceInstanceDTO dto); 
	
	 @GetMapping("/_probe")
	    String probe();
}
