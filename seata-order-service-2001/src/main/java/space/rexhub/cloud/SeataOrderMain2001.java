package space.rexhub.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Description: 主启动类
 *
 * @author Rex
 * @date 2025-06-13
 */
@SpringBootApplication
@MapperScan("space.rexhub.cloud.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class SeataOrderMain2001 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMain2001.class, args);
    }
}