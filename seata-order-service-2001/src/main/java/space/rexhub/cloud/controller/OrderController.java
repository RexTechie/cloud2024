package space.rexhub.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.rexhub.cloud.entities.Order;
import space.rexhub.cloud.resp.R;
import space.rexhub.cloud.service.OrderService;

/**
 * Description: 订单控制器
 *
 * @author Rex
 * @date 2025-06-14
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     * @param order 订单
     * @return 创建订单的结果
     */
    @GetMapping("/order/create")
    public R create(Order order) {
        orderService.create(order);
        return R.success(order);
    }
}
