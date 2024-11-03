# 家常菜后台服务-Home-Cuisine

## 核心技术栈

| 软件名称               | 描述          | 版本
|--------------------|-------------|---
| Jdk                | Java环境      | 1.8
| Spring Boot        | 开发框架        | 2.2.2.RELEASE
| SpringCloudAlibaba | 微服务框架       | 2.2.0.RELEASE
| Redis              | 分布式缓存       | 3.2.8 或 高版本
| MySQL              | 数据库         | 5.7.44
| MQ                 | 消息中间件       | Kafka 或 RabbitMQ 
| Vue                | 前端开发使用      | 2.1.2
| MyBatis-Plus       | MyBatis增强工具 | 3.4.2
| ElasticSearch      | 搜索引擎        | /
| Docker             | 镜像构建管理      | /
| Kubernates         | 服务管理        | /
| Jenkins            | CI流程编排工具    | /
| Nginx              | 前端部署工具      | /
| [Jeepay](https://github.com/jeequan/jeepay)         | 支付工具        | /



## 微服务拆分
```lua
home-cuisine
└── user - 用户服务
    ├── 认证鉴权服务
    ├── 注册维护用户信息
└── merchant - 商家服务
    ├── 入驻
    ├── 信息维护
├── waiter - 小二服务
├── menu - 菜单服务
├── order - 订单服务
├── pay - 支付服务-[Jeepay]
└── evaluate - 评价服务

```

  
