package space.rexhub.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: Feign配置类
 *
 * @author Rex
 * @date 2025-05-29
 */
@Configuration
public class FeignConfig {
    /**
     * Feign重试器配置
     * @return Feign重试器
     */
    @Bean
    public Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
//         最大请求次数为3，初始间隔时间为100ms，重试间最大间隔时间为1s
//        return new Retryer.Default(100, 1, 3);
    }

    /**
     * Feign HTTP客户端配置
     * @return HttpClient5FeignConfiguration
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
