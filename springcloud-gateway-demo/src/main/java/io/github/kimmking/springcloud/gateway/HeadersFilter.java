package io.github.kimmking.springcloud.gateway;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;


@Component
public class HeadersFilter implements GlobalFilter, Ordered {

    static Logger logger = LoggerFactory.getLogger(HeadersFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("HeadersFilter");

        //HttpHeaders headers = exchange.getRequest().getHeaders();

        ServerHttpRequest request = exchange.getRequest().mutate().header("exchange", "kimmking.github.io").build();
        logger.info("Add header exchange->" + request.getHeaders().getFirst("exchange")+" success");
        return chain.filter(exchange.mutate().request(request).build());

        //return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
