package space.rexhub.cloud.api;

import org.springframework.stereotype.Component;
import space.rexhub.cloud.entity.PayDTO;
import space.rexhub.cloud.resp.R;
import space.rexhub.cloud.resp.ReturnCodeEnum;

/**
 * Description: Fallback class for PayFeignSentinelApi.
 *
 * @author Rex
 * @date 2025-06-12
 */
@Component
public class PayFeignSentinelApiFallback implements PayFeignSentinelApi{

    @Override
    public R<PayDTO> getPayByOrderNo(String orderNo) {
        return R.error(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，Fallback服务降级处理，请稍后再试！");
    }
}
