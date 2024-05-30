package com.example.apigateway.filter;

import com.example.apigateway.dto.Order;
import com.example.apigateway.dto.Product;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RequestFilter implements GatewayFilter {
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Object body = exchange.getAttribute("cachedRequestBodyObject");
        System.out.println("in request filter");
        if(body instanceof Order) {
            System.out.println("body:" + (Order) body);
        }
        else if(body instanceof Product) {
            System.out.println("body:" + (Product) body);
        }
        return chain.filter(exchange);
    }
}
