package com.oss.order.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
@Builder
public class DeviceInstanceDTO {
    private Long id;
    private String model;
    private String status;
    private String deviceType;
    private Long usedForId;
    
}
