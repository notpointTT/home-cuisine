# 家常菜后台服务-Home-Cuisine

## 核心技术栈

| 软件名称                                        | 描述          | 版本
|---------------------------------------------|-------------|---
| Jdk                                         | Java环境      | 1.8
| Spring Boot                                 | 开发框架        | 2.2.2.RELEASE
| Spring Cloud Alibaba                        | 微服务框架       | 2.2.1.RELEASE
| Nacos                                       | 微服务注册配置中心   | 1.2.1
| Sentinel                                    | 微服务限流降级工具   | 1.7.1
| Seata                                       | 微服务分布式事务工具  | 1.2.0
| Redis                                       | 分布式缓存       | 3.2.8 或 高版本
| MySQL                                       | 数据库         | 5.7.44
| MQ                                          | 消息中间件       | Kafka 或 RabbitMQ 
| Vue                                         | 前端开发使用      | 2.1.2
| MyBatis-Plus                                | MyBatis增强工具 | 3.4.2
| ElasticSearch                               | 搜索引擎        | /
| Docker                                      | 镜像构建管理      | /
| Kubernates                                  | 服务管理        | /
| Jenkins                                     | CI流程编排工具    | /
| Nginx                                       | 前端部署工具      | /
| [Jeepay](https://github.com/jeequan/jeepay) | 支付工具        | /



## 微服务拆分
```markdown
home-cuisine（聚合POM）
├── commons/          # 通用基础模块
│   ├── core          # 核心工具
│   ├── security      # 安全认证
│   └── dto           # 统一数据模型
├── infra/            # 基础设施
│   ├── gateway       # 网关服务
│   └── mq            # 消息队列
├── auth/             # 认证中心
│   ├── user          # 用户服务（登录）
│   └── social        # 社交登录（非部署服务，代码被 user 依赖）
├── business/         # 业务服务
│   ├── dish          # 菜品服务
│   ├── order         # 订单服务
│   ├── payment       # 支付服务
│   ├── merchant      # 商家后台
│   └── delivery      # 配送服务（预留）
└── external/         # 第三方对接
    ├── wechat        # 微信
    ├── alipay        # 支付宝
    └── sms           # 短信服务

```


![img.png](./.readme/模块依赖图.png)

![img.png](./.readme/下单流程图.png)

![img.png](./.readme/支付流程图.png)
