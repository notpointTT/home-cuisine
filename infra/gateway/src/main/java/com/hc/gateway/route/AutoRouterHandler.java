package com.hc.gateway.route;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AutoRouterHandler {

    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher applicationEventPublisher;

    private static final List<String> routeList = new ArrayList<>();

    public AutoRouterHandler(RouteDefinitionWriter routeDefinitionWriter, ApplicationEventPublisher applicationEventPublisher) {
        this.routeDefinitionWriter = routeDefinitionWriter;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    /**
     * 更新所有路由信息
     *
     * @param configStr
     */
    public void refreshAll(String configStr) {
        log.info("start refreshAll : {}", configStr);
        // 无效字符串不处理
        if (!StringUtils.hasText(configStr)) {
            log.error("invalid string for route config");
            return;
        }
        // 用Jackson反序列化
        List<RouteDefinition> routeDefinitions = null;
        try {
            routeDefinitions = JSONObject.parseArray(configStr, RouteDefinition.class);
        } catch (Exception e) {
            log.error("get route definition from nacos string error", e);
        }
        // 如果等于null，表示反序列化失败，立即返回
        if (null == routeDefinitions) {
            return;
        }
        // 清理掉当前所有路由
        clear();
        // 添加最新路由
        add(routeDefinitions);

        // 通过应用内消息的方式发布
        publish();

        log.info("finish refreshAll");
    }


    /**
     * 清理集合中的所有路由，并清空集合
     */
    private void clear() {
        // 全部调用API清理掉
        try {
            routeList.forEach(id -> routeDefinitionWriter.delete(Mono.just(id)).subscribe());
        } catch (Exception e) {
            log.error("clear Route is error !");
        }
        // 清空集合
        routeList.clear();
    }

    /**
     * 新增路由
     *
     * @param routeDefinitions
     */
    private void add(List<RouteDefinition> routeDefinitions) {

        try {
            routeDefinitions.forEach(routeDefinition -> {
                routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
                routeList.add(routeDefinition.getId());
            });
        } catch (Exception exception) {
            log.error("add route is error", exception);
        }
    }

    /**
     * 发布进程内通知，更新路由
     */
    private void publish() {
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(routeDefinitionWriter));
    }

}
