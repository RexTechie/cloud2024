package space.rexhub.cloud.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.rexhub.cloud.mapper.StorageMapper;
import space.rexhub.cloud.service.StorageService;

/**
 * Description: StorageServiceImpl
 *
 * @author Rex
 * @date 2025-06-14
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     *
     * @param productId 商品ID
     * @param count     扣减数量
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("-------> StorageServiceImpl.decrease 扣减库存开始");
        storageMapper.decrease(productId, count);
        log.info("-------> StorageServiceImpl.decrease 扣减库存结束");
    }
}
