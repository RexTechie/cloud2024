package space.rexhub.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Rex
 * @date 2025-06-05
 */
@RestController
public class PayCircuitController {

    /**
     * Resilience4j 熔断器示例
     * @param id 熔断器ID
     * @return 熔断器响应
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("ID不能为负数");
        }
        if (id > 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, circuit! inputId: " + id + "\t" + IdUtil.simpleUUID() + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * Resilience4j Bulkhead例子
     * @param id 熔断器ID
     * @return 熔断器响应
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("ID不能为负数");
        }
        if (id > 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, bulkhead! inputId: " + id + "\t" + IdUtil.simpleUUID() + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * Resilience4j RateLimiter例子
     * @param id 熔断器ID
     * @return 熔断器响应
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRateLimit(@PathVariable("id") Integer id) {
        return "Hello, my rateliminit 欢迎来到 inputId: " + id + "\t" + IdUtil.simpleUUID();
    }
}
