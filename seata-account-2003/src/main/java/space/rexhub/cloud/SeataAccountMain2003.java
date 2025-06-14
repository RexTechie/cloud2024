package space.rexhub.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Description: 启动类
 *
 * @author Rex
 * @date 2025-06-14
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("space.rexhub.cloud.mapper")
public class SeataAccountMain2003 {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccountMain2003.class, args);
    }
}