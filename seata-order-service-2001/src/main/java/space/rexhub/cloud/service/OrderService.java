package space.rexhub.cloud.service;

import space.rexhub.cloud.entities.Order;

/**
 * Description: 创建订单
 *
 * @author Rex
 * @date 2025-06-13
 */
public interface OrderService {
    void create(Order order);
}
