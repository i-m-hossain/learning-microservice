package com.imran.product_service.service;

import com.imran.product_service.dto.ProductRequest;
import com.imran.product_service.dto.ProductResponse;
import com.imran.product_service.model.Product;
import com.imran.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts() {

         List<Product> products =    productRepository.findAll();
         return products.stream().map(product -> {
             return ProductResponse
                     .builder()
                     .id(product.getId())
                     .name(product.getName())
                     .description(product.getDescription())
                     .price(product.getPrice())
                     .build();
         }).toList();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }
}
