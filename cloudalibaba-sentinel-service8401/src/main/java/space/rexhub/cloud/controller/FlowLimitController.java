package space.rexhub.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.rexhub.cloud.service.FlowLimitService;

import java.util.concurrent.TimeUnit;

/**
 * Description: 流量控制测试控制器
 *
 * @author Rex
 * @date 2025-06-09
 */
@RestController
@Slf4j
public class FlowLimitController {

    /**
     * 流控-链路演示Demo
     * C和D两个请求都访问flowLimitController中的common方法，
     */
    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        log.info("testA 流量控制测试");
        return "-----testA";
    }
    @GetMapping("/testB")
    public String testB() {
        log.info("testB 流量控制测试");
        return "-----testB";
    }

    @GetMapping("/testC")
    public String testC() {
        log.info("testC 流量控制测试");
        flowLimitService.common();
        return "-----testC";
    }
    @GetMapping("/testD")
    public String testD() {
        log.info("testD 流量控制测试");
        flowLimitService.common();
        return "-----testD";
    }

    @GetMapping("/testE")
    public String testE(){
        System.out.println(System.currentTimeMillis() + "  testE 流量控制测试 -- 排队等待");
        return "-----testE";
    }

    /**
     * 新增熔断规则-慢调用比例
     *
     */
    @GetMapping("/testF")
    public String testF(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----测试：新增熔断规则-慢调用比例");
        return "-----testF";
    }

    /**
     * 新增熔断规则-异常比例
     * 故意制造异常，触发熔断规则
     */
    @GetMapping("/testG")
    public String testG() {
        System.out.println("----测试：新增熔断规则-异常比例");
        int age = 10 / 0; // 故意制造异常
        return "-----testG";
    }

    @GetMapping("/testH")
    public String testH() {
        System.out.println("----测试：新增熔断规则-异常数");
        int age = 10 / 0; // 故意制造异常
        return "-----testH";
    }
}
