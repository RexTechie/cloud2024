package space.rexhub.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: Nacos配置客户端控制器
 *
 * @author Rex
 * @date 2025-06-09
 */
@RestController
@RefreshScope // 在控制器类加入@RefreshScope注解，使当前类下的配置支持Nacos的动态刷新功能
public class NacosConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
