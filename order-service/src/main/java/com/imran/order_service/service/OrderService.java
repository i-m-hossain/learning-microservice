package com.imran.order_service.service;

import com.imran.order_service.dto.InventoryResponse;
import com.imran.order_service.dto.OrderLineItemsDto;
import com.imran.order_service.dto.OrderRequest;
import com.imran.order_service.model.Order;
import com.imran.order_service.model.OrderLineItems;
import com.imran.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public List<Order> getAllOrders() {
       
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);
        List<String> skuCodes = orderLineItems.stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        List<InventoryResponse> inventoryList = inventoryClient.getInventoryList(skuCodes);
        boolean allProductsInStock = !inventoryList.isEmpty() && inventoryList.stream().allMatch(InventoryResponse::isInStock);

        // if only all products are in inventory, we will place an order
        if(allProductsInStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("All products are not in stock, please try again later");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
