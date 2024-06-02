package com.example.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Configuration
public class GlobalFilterConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalFilterConfiguration.class);
    @Bean
    public GlobalFilter customGlobalFilter(){
        return (exchange, chain) -> {
            //actual request from client
            logRequest(exchange);

            //adding some custom headers from api gateway
            ServerWebExchange modifiedRequest = addCustomRequestHeader(exchange);
            logRequest(modifiedRequest);

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logResponse(exchange);
            }));
        };
    }
    private void logRequest(ServerWebExchange exchange){
        LOGGER.info("Request Path: {}", exchange.getRequest().getPath());
        exchange.getRequest().getHeaders().forEach((name, values)->values.forEach(value-> LOGGER.info("Request header: {} = {}", name, value)));
    }
    private void logResponse(ServerWebExchange exchange) {
        LOGGER.info("Response Status code: {}", exchange.getResponse().getStatusCode());
        exchange.getResponse().getHeaders().forEach((name, values) -> values.forEach(value -> LOGGER.info("Response header: {} = {}", name, value)));
    }
    private ServerWebExchange addCustomRequestHeader(ServerWebExchange exchange){
        ServerHttpRequest request = exchange.getRequest().mutate().header("X-Custom-Header","From api gateway" ).build();
        return exchange.mutate().request(request).build();
    }
}
