package com.imran.inventory_service;

import com.imran.inventory_service.model.Inventory;
import com.imran.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@EnableDiscoveryClient
@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			List<Inventory> inventories = generateDummyData();
			if(inventoryRepository.count() == 0){
				inventories.forEach(inventoryRepository::save);
			}
		};
	}

	private static List<Inventory> generateDummyData() {
		List<Inventory> products = new ArrayList<>(100);
		Random random = new Random();
		String[] sampleSkus = {
				"ABC123", "XYZ987", "LMN456", "JKL789", "QWE321", "RTY654", "UIO963", "PAS852", "DFG741", "HJK159"
		};
		for (int i = 0; i < 100; i++) {
			Inventory product = new Inventory();
			product.setQuantity(random.nextInt(100) + 1);
			product.setSkuCode(sampleSkus[random.nextInt(sampleSkus.length)] + "-" + (i + 1));
			products.add(product);
		}
		return products;
	}
}
