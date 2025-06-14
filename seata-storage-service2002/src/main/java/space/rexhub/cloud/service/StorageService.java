package space.rexhub.cloud.service;

/**
 * Description: StorageService
 *
 * @author Rex
 * @date 2025-06-14
 */
public interface StorageService {
    /**
     * 扣减库存
     *
     * @param productId 商品ID
     * @param count 扣减数量
     */
    void decrease(Long productId, Integer count);
}
