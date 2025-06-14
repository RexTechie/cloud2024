package space.rexhub.cloud.controller;

import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.rexhub.cloud.api.PayFeignApi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Description: 订单断路器控制器
 *
 * @author Rex
 * @date 2025-06-05
 */
@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud_payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    /**
     * 兜底方案
     * @param id 熔断器ID
     * @param throwable 异常信息
     * @return 回退响应
     */
    public String myCircuitFallback(Integer id, Throwable throwable) {
        // 这里可以记录日志或执行其他操作
        return "服务不可用，请稍后再试，ID: " + id + ", 错误信息: " + throwable.getMessage();
    }

//    /**
//     * Resilience4j Bulkhead例子
//     * @param id 熔断器ID
//     * @return 熔断器响应
//     */
//    @GetMapping(value = "/feign/pay/bulkhead/{id}")
//    @Bulkhead(name = "cloud_payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
//    public String myBulkhead(@PathVariable("id") Integer id) {
//        return payFeignApi.myBulkhead(id);
//    }
//
//    public String myBulkheadFallback(Integer id, Throwable throwable) {
//        // 这里可以记录日志或执行其他操作
//        return "服务不可用，请稍后再试，ID: " + id + ", 错误信息: " + throwable.getMessage();
//    }
    /**
     * Resilience4j Bulkhead例子
     * @param id 熔断器ID
     * @return 熔断器响应
     */
    @GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud_payment-service", fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadThreadPool(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + "\t" + "调用myBulkheadThreadPool方法，ID: " + id);
        try {
            // 模拟延迟
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "完成myBulkheadThreadPool方法，ID: " + id);
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t"  + "Bulkhead.type.THREADPOOL");
    }

    public CompletableFuture<String> myBulkheadPoolFallback(Integer id, Throwable throwable) {
        // 这里可以记录日志或执行其他操作
        return CompletableFuture.supplyAsync(()->"myBulkheadPoolFallback: 服务不可用" + "\t ID: " + id +"，请稍后再试, 错误信息: " + throwable.getMessage());
    }

    @GetMapping(value = "/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud_payment-service", fallbackMethod = "myRateLimitFallback")
    public String myRateLimit(@PathVariable("id") Integer id){
        return payFeignApi.myRateLimit(id);
    }

    public String myRateLimitFallback(Integer id, Throwable throwable) {
        // 这里可以记录日志或执行其他操作
        return "服务不可用，请稍后再试，ID: " + id + ", 错误信息: " + throwable.getMessage();
    }

}
