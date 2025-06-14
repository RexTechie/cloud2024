package space.rexhub.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.rexhub.cloud.api.PayFeignApi;
import space.rexhub.cloud.resp.R;

/**
 * Description: 订单网关控制器
 *
 * @author Rex
 * @date 2025-06-06
 */
@RestController
public class OrderGatewayController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/gateway/get/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return payFeignApi.getById(id);
    }

    @GetMapping(value = "/feign/pay/gateway/info")
    public R<String> getGatewayInfo() {
        return payFeignApi.getGatewayInfo();
    }
}
