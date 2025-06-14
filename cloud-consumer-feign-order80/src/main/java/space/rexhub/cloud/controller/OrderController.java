package space.rexhub.cloud.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import space.rexhub.cloud.api.PayFeignApi;
import space.rexhub.cloud.entity.PayDTO;
import space.rexhub.cloud.resp.R;
import space.rexhub.cloud.resp.ReturnCodeEnum;

/**
 * Description: 订单服务
 *
 * @author Rex
 * @date 2025-05-23
 */
@Tag(name="订单服务", description="订单服务")
@RestController
@Slf4j
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    /**
     * 添加订单
     * @param payDTO 订单信息
     * @return 订单响应
     */
    @PostMapping(value = "/feign/pay/add")
    public R addOrder(@RequestBody PayDTO payDTO) {
        log.info("接收到订单请求: {}", payDTO);
        // 调用支付服务的Feign接口
        return payFeignApi.addPay(payDTO);
    }

    /**
     * 获取支付信息
     * @param id 支付ID
     * @return 支付信息响应
     */
    @GetMapping(value = "/feign/pay/get/{id}")
    public R getPayInfo(@PathVariable("id") Integer id){
        log.info("查询支付信息，ID: {}", id);

        try {
            System.out.println("调用开始：" + DateUtil.now());
            return payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            System.out.println("调用异常：" + DateUtil.now());
//            e.printStackTrace();
            log.error("查询支付信息异常: {}", e.getMessage());
            return R.error(ReturnCodeEnum.RC500);
        }
    }

    /**
     * 获取支付服务的负载均衡信息
     * @return 负载均衡信息
     */
    @GetMapping(value = "/feign/pay/myLB")
    public String myLB(){
        log.info("获取支付服务的负载均衡信息");
        return payFeignApi.myLB();
    }
}
