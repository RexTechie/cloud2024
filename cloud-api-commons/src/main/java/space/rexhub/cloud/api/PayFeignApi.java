package space.rexhub.cloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import space.rexhub.cloud.entity.PayDTO;
import space.rexhub.cloud.resp.R;

/**
 * Description: Feign client for payment service.
 *
 * @author Rex
 * @date 2025-05-28
 */
//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway-service")
public interface PayFeignApi {

    /**
     * 插入支付流水
     * @param payDTO 支付流水
     * @return 成功插入支付流水，返回值
     */
    @PostMapping("/pay/add")
    R addPay(@RequestBody PayDTO payDTO);

    /**
     * 查询支付流水
     * @param id 支付流水ID
     * @return 支付流水
     */
    @GetMapping("pay/get/{id}")
    R getPayInfo(@PathVariable("id") Integer id);

    /**
     * openfeign天然支持负载均衡
     * @return 负载均衡的服务信息
     */
    @GetMapping("pay/get/info")
    String myLB();

    /**
     * Resilience4j 熔断器示例
     * @param id 熔断器ID
     * @return 熔断器响应
     */
    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead例子
     * @param id 熔断器ID
     * @return 熔断器响应
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);


    /**
     * Resilience4j RateLimiter例子
     * @param id 熔断器ID
     * @return 熔断器响应
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRateLimit(@PathVariable("id") Integer id);

    /**
     * 微服务（Sleuth）进行链路监控的例子
     * @param id 微服务ID
     * @return 返回字符串
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    /**
     * Gateway进行网关测试案例01
     * @param id 网关ID
     * @return 返回字符串
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    R getById(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/gateway/info")
    R<String> getGatewayInfo();


}
