package space.rexhub.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import space.rexhub.cloud.resp.R;
import space.rexhub.cloud.service.StorageService;

/**
 * Description: StorageController
 *
 * @author Rex
 * @date 2025-06-14
 */
@RestController
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;

    @PostMapping(value = "/storage/decrease")
    public R<String> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        storageService.decrease(productId, count);
        return R.success("扣减库存成功");
    }
}
