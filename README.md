# Spring Cloud 学习记录

本项目包含了Spring Cloud相关框架和技术的学习代码及笔记，用于记录学习Spring Cloud微服务架构的过程。

## 学习笔记

- Spring Cloud: https://rextechie.github.io/posts/java/springcloud/
- Spring Cloud Alibaba: https://rextechie.github.io/posts/java/springcloudalibaba/

## 依赖版本

- JDK: 17
- Spring Boot: 3.2.4
- Spring Cloud: 2023.0.1
- Spring Cloud Alibaba: 2023.0.1.0

## 项目结构

项目主要包含以下模块：

### Spring Cloud 组件的使用

- **cloud-api-commons**：公共API模块，包含各服务共用的实体类和工具类
- **cloud-provider-payment8001/8002/8003**：支付服务提供者（多实例）
- **cloud-consumer-order80**：订单消费者服务
- **cloud-consumer-feign-order80**：使用OpenFeign的订单消费者服务
- **cloud-gateway9527**：API网关服务

### Spring Cloud Alibaba 组件的使用

- **cloudalibaba-provider-payment9001**：基于Nacos的支付服务提供者
- **cloudalibaba-consumer-nacos-order83**：基于Nacos的消费者服务
- **cloudalibaba-config-nacos-client3377**：Nacos配置中心客户端
- **cloudalibaba-sentinel-service8401**：Sentinel服务限流熔断
- **cloudalibaba-sentinel-gateway9528**：整合Sentinel的网关服务
- **seata-order-service-2001**：订单服务
- **seata-storage-service2002**：库存服务
- **seata-account-2003**：账户服务

## 学习内容

1. 服务注册发现&服务配置：Consul/Nacos
2. 负载均衡：LoadBalancer
3. 服务调用：OpenFeign
4. 服务熔断与降级：Resilience4j/Sentinel
5. 分布式链路追踪：Sleuth(Micrometer)+Zipkin
6. 服务网关：Gateway
7. 分布式事务：Seata
