package com.example.apigateway;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfiguration {
    @Bean
    public GlobalFilter customGlobalFilter(){
        return (exchange, chain) -> {
            System.out.println("Global Pre Filter executed");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("Global Post Filter executed"+exchange);
            }));
        };
    }
}
