package space.rexhub.cloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import space.rexhub.cloud.resp.R;

/**
 * Description: 库存服务Feign接口
 *
 * @author Rex
 * @date 2025-06-13
 */
@FeignClient("seata-storage-service")
public interface StorageFeignApi {

    /**
     * 扣减库存
     *
     * @param productId 产品ID
     * @param count     扣减数量
     * @return 响应结果
     */
    @PostMapping(value = "/storage/decrease")
    R<String> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
