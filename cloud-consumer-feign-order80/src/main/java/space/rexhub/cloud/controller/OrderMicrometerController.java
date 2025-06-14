package space.rexhub.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.rexhub.cloud.api.PayFeignApi;

/**
 * Description: TODO
 *
 * @author Rex
 * @date 2025-06-06
 */
@RestController
public class OrderMicrometerController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return payFeignApi.myMicrometer(id);
    }
}
