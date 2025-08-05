package com.hc.gateway.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoRouterConfig {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public AutoRouterHandler autoRouterHandler() {
        return new AutoRouterHandler(routeDefinitionWriter, applicationEventPublisher);
    }

    @Bean
    public NacosRouteConfigListener nacosRouteConfigListener() {
        return new NacosRouteConfigListener(autoRouterHandler());
    }

}
