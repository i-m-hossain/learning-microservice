package com.imran.order_service.service;

import com.imran.order_service.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/api/inventories")
    List<InventoryResponse> getInventoryList(@RequestParam("skuCode") List<String> skuCodes);
}
