package space.rexhub.cloud.service.impl;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import space.rexhub.cloud.api.AccountFeignApi;
import space.rexhub.cloud.api.StorageFeignApi;
import space.rexhub.cloud.entities.Order;
import space.rexhub.cloud.mapper.OrderMapper;
import space.rexhub.cloud.service.OrderService;
import tk.mybatis.mapper.entity.Example;

/**
 * Description: 订单服务实现类
 *
 * @author Rex
 * @date 2025-06-13
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AccountFeignApi accountFeignApi; // 账户微服务api

    @Resource
    private StorageFeignApi storageFeignApi; // 库存微服务api
    @Autowired
    private WebRequest webRequest;

    @Override
    @GlobalTransactional(name = "rex-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        // XID全局事务id的检查，重要
        String xid = RootContext.getXID();
        // 1. 新建订单
        log.info("-----------------开始新建订单：\txid: {}", xid);
        // 订单新建时默认初始订单状态是0
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        // 插入订单成功后获得mysql实体
        Order orderFromDB = null;
        if(result > 0) {
            orderFromDB = orderMapper.selectOne(order);
            log.info("------------> 新建订单成功，orderFromDB info: {}", orderFromDB);
            // 2. 扣减库存
            log.info("------------> 订单微服务开始调用Storage，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("------------> 订单微服务调用Storage，扣减count成功");
            System.out.println();
            // 3. 扣减账户余额
            log.info("------------> 订单微服务开始调用Account，做扣减余额");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("------------> 订单微服务调用Account，扣减余额成功");
            System.out.println();
            // 4. 修改订单状态为1，表示已完成
            log.info("-------------> 修改订单状态");
            orderFromDB.setStatus(1);
            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId", orderFromDB.getUserId());
            criteria.andEqualTo("status", 0);
            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);

            log.info("------------> 修改订单状态完成: {}", updateResult);
            log.info("------------> orderFromDB info: {}", orderFromDB);
        }
        System.out.println();
        log.info("-----------------结束新建订单：\txid: {}", xid);
    }
}
