package space.rexhub.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 限流测试控制器
 *
 * @author Rex
 * @date 2025-06-10
 */
@RestController
@Slf4j
public class RateLimitController {
    @GetMapping("/rateLimit/byUrl")
    public String byUrl(){
        return "按rest地址限流测试OK";
    }

    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResourceSentinelResource", blockHandler = "handlerBlockHandler")
    public String byResource(){
        return "按资源名称SentinelResource限流测试OK";
    }

    public String handlerBlockHandler(BlockException e){
        return "服务不可用，触发了@SentinelResource启动。";
    }

    @GetMapping("/rateLimit/doAction/{p1}")
    @SentinelResource(value = "doActionSentinelResource", blockHandler = "doActionBlockHandler", fallback = "doActionFallback")
    public String doAction(@PathVariable("p1") Integer p1) {
        if (p1 == 0) {
            throw new RuntimeException("p1等于零直接异常");
        }
        return "doAction";
    }

    public String doActionBlockHandler(@PathVariable(value = "p1") Integer p1, BlockException e) {
        log.error("sentinel配置自定义限流了: {}", e.getMessage());
        return "sentinel配置自定义限流了";
    }
    public String doActionFallback(@PathVariable("p1") Integer p1, Throwable e) {
        log.error("程序逻辑异常了：{}", e);
        return "程序逻辑异常了\t" + e.getMessage();

    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "-----testHotKey, p1: " + p1 + ", p2: " + p2;
    }

    public String dealHandler_testHotKey(String p1, String p2, BlockException e) {
        return "-----dealHandler_testHotKey, p1: " + p1 + ", p2: " + p2 + ", 限流了";
    }
}
