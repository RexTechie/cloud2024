package space.rexhub.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Description: 全局过滤器，用于记录访问接口的日志信息
 *
 * @author Rex
 * @date 2025-06-06
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public static final String BEGIN_VISIT_TIME = "begin_visit_time"; // 开始调用方法的时间

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 记录开始调用方法的时间
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        // 2. 返回统计的各个结果
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long beginVisitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (beginVisitTime != null) {
                log.info("访问接口主机：{}", exchange.getRequest().getURI().getHost());
                log.info("访问接口端口：{}", exchange.getRequest().getURI().getPort());
                log.info("访问接口路径：{}", exchange.getRequest().getURI().getPath());
                log.info("访问接口URL参数：{}", exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口时长：{} 毫秒", System.currentTimeMillis() - beginVisitTime);
                log.info("===========================================");
            } else {
                log.warn("未找到开始调用方法的时间戳");
            }
        }));
    }

    /**
     * 数字越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
