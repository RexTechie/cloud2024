package space.rexhub.cloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import space.rexhub.cloud.entity.PayDTO;
import space.rexhub.cloud.resp.R;

/**
 * Description:
 *
 * @author Rex
 * @date 2025-06-12
 */
@FeignClient(value = "nacos-payment-provider", fallback = PayFeignSentinelApiFallback.class)
public interface PayFeignSentinelApi {

    @GetMapping("/pay/nacos/get/{orderNo}")
    R<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
