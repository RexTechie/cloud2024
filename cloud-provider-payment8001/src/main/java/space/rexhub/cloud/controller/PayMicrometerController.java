package space.rexhub.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 微服务（Sleuth）进行链路监控的例子
 *
 * @author Rex
 * @date 2025-06-06
 */
@RestController
public class PayMicrometerController {

    /**
     * 微服务（Sleuth）进行链路监控的例子
     * @param id 微服务ID
     * @return 返回字符串
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return "Hello, 欢迎来到myMicrometer, 你的ID是：" + id + "，服务返回：" + IdUtil.simpleUUID();
    }
}
