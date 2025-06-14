package space.rexhub.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: EmpowerController
 *
 * @author Rex
 * @date 2025-06-10
 */
@RestController
@Slf4j
public class EmpowerController {
    @GetMapping("/empower")
    public String requestSentinel4() {
        log.info("测试Sentinel授权规则empower");
        return "sentinel授权规则";
    }
}
