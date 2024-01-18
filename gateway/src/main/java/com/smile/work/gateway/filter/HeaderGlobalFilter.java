package com.smile.work.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/1/18 20:45
 */
public class HeaderGlobalFilter implements GlobalFilter, Ordered {
    /**
    * 链路key
    **/
    private static final String TRACE_KEY = "traceId";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
