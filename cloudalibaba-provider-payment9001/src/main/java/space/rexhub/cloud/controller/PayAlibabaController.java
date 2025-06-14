package space.rexhub.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.rexhub.cloud.entity.PayDTO;
import space.rexhub.cloud.resp.R;
import space.rexhub.cloud.resp.ReturnCodeEnum;

import java.math.BigDecimal;

/**
 * Description: 支付控制器
 *
 * @author Rex
 * @date 2025-06-09
 */
@RestController
public class PayAlibabaController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") String id) {
        return "nacos registry, port: " + port + "\t" + "id: " + id + ", O(∩_∩)O哈哈~";
    }

    // openfeign+sentinel进行服务降级和流量监控的整合处理case

    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayOrderNo", blockHandler = "handlerBlockHandler")
    public R<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        // 模拟从数据库查询出数据并赋值DTO
        PayDTO payDTO = new PayDTO();
        payDTO.setId(1024);
        payDTO.setOrderNo(orderNo);
        payDTO.setAmount(BigDecimal.valueOf(9.9));
        payDTO.setPayNo("pay:"+ IdUtil.fastUUID());
        payDTO.setUserId(1);
        return R.success(payDTO);
    }

    public R<Void> handlerBlockHandler(@PathVariable("getByOrderNo") String orderNo, BlockException e) {
        return R.error(ReturnCodeEnum.RC500.getCode(), "getPayByOrderNo服务不可用，触发sentinel流控配置规则，请稍后再试！");
    }
}
