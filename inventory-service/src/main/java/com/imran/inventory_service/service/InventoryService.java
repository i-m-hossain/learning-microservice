package com.imran.inventory_service.service;

import com.imran.inventory_service.entity.Inventory;
import com.imran.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryByProductId(String productId) {
        return inventoryRepository.findById(productId).orElse(null);
    }

    public Inventory updateInventory(String productId, int quantity) {
        Inventory inventory = inventoryRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        inventory.setQuantity(quantity);
        return inventoryRepository.save(inventory);
    }
}
