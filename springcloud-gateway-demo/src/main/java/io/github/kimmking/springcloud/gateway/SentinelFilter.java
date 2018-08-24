package io.github.kimmking.springcloud.gateway;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;


@Component
public class SentinelFilter implements GlobalFilter, Ordered {

    static Logger logger = LoggerFactory.getLogger(SentinelFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("Sentinel filter...");

//        HttpHeaders headers = exchange.getRequest().getHeaders();
//        String test = headers.get("test").get(0);
//        if (mapping.containsKey(test)) {
//            ServerHttpRequest request = exchange.getRequest().mutate().header("exchange-cloud", mapping.get(test)).build();
//            logger.info("success");
//            return chain.filter(exchange.mutate().request(request).build());
//        }

        URI uri = exchange.getRequest().getURI();
        String hostResource = uri.getScheme() + "://" + uri.getHost() + ":" + (uri.getPort() == -1 ? 80 : uri.getPort());
        String hostWithPathResource = hostResource + uri.getPath();
        Entry hostEntry = null, hostWithPathEntry = null;

        try {
            ContextUtil.enter(hostWithPathResource);
            hostWithPathEntry = SphU.entry(hostWithPathResource);
            hostEntry = SphU.entry(hostResource);
        }
        catch (BlockException e) {
                logger.error("sentinel handle BlockException error.", e);
                throw new RuntimeException(e);
        }
        finally {
            if (hostEntry != null) {
                hostEntry.exit();
            }
            if (hostWithPathEntry != null) {
                hostWithPathEntry.exit();
            }
            ContextUtil.exit();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -10000;
    }
}
