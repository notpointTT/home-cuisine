package com.hc.gateway.route;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.Executor;

@Slf4j
public class NacosRouteConfigListener {

    @Value("${spring.cloud.nacos.server-addr}")
    private String serverAddr;
    @Value("${spring.cloud.nacos.config.namespace:public}")
    private String namespace;
    @Value("${spring.cloud.nacos.config.group:DEFAULT_GROUP}")
    private String group;

    private AutoRouterHandler autoRouterHandler;

    public NacosRouteConfigListener(AutoRouterHandler autoRouterHandler) {
        this.autoRouterHandler = autoRouterHandler;
    }

    @PostConstruct
    public void dynamicRouteByNacosListener() throws NacosException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, namespace);
        ConfigService configService = NacosFactory.createConfigService(properties);
        // 添加监听，nacos上的配置变更后会执行
        String DATA_ID = "gateway-routes";
        configService.addListener(DATA_ID, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                // 解析和处理都交给RouteOperator完成
                try {
                    autoRouterHandler.refreshAll(configInfo);
                }catch (Exception e) {
                    log.error("路由刷新失败", e);
                }
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });

        // 默认初始化 Nacos 配置路由
        try {
            String configInfo = configService.getConfig(DATA_ID, group, 5000);
            autoRouterHandler.refreshAll(configInfo);
        }catch (Exception e) {
            log.error("路由刷新失败", e);
        }
    }

}
