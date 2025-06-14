package space.rexhub.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Description: 主启动类
 *
 * @author Rex
 * @date 2025-05-23
 */
@SpringBootApplication
@EnableDiscoveryClient // 启用服务发现
@RefreshScope // Consul 动态刷新
@MapperScan("space.rexhub.cloud.mapper")
public class Main8003 {
    public static void main(String[] args) {
        SpringApplication.run(Main8003.class, args);
    }
}