package space.rexhub.cloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import space.rexhub.cloud.resp.R;

/**
 * Description:
 *
 * @author Rex
 * @date 2025-06-13
 */
@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {
    /**
     * 扣减账户余额
     *
     * @param userId 用户ID
     * @param money  扣减金额
     * @return 响应结果
     */
    @PostMapping("/account/decrease")
    R<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
