package com.example.apigateway.configuration;

import com.example.apigateway.dto.Order;
import com.example.apigateway.dto.Product;
import com.example.apigateway.filter.PostGlobalFilter;
import com.example.apigateway.filter.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfiguration {
    @Autowired
    RequestFilter requestFilter;
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        // adding 2 rotes to first microservice as we need to log request body if method is POST
        return builder.routes()
                .route("product-microservice",r -> r.path("/products")
                        .and().method("POST")
                        .and().readBody(Product.class, s -> true)
                        .filters(f -> f.filters(requestFilter))
                        .uri("lb://products"))
                .route("product-microservice",r -> r.path("/products")
                        .and().method("GET")
//                        .filters(f-> f.filters(authFilter))
                        .uri("lb://products"))
                .route("order-microservice",r -> r.path("/orders")
                        .and().method("GET")
//                        .and().readBody(Company.class, s -> true).filters(f -> f.filters(requestFilter, authFilter))
                        .uri("lb://orders"))
                .route("order-microservice",r -> r.path("/orders")
                        .and().method("POST")
                        .and().readBody(Order.class, s->true)
                        .filters(f-> f.filters(requestFilter))
                        .uri("lb://orders"))
                .route("inventory-microservice",r -> r.path("/inventory")
                        .and().method("GET")
                        .uri("lb://inventory"))
                .build();
    }

    // Key resolver to use client's IP address for rate limiting
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
    @Bean
    public WebFilter responseFilter(){
        return new PostGlobalFilter();
    }
}
