package space.rexhub.cloud.mapper;

import org.apache.ibatis.annotations.Param;
import space.rexhub.cloud.entities.Storage;
import tk.mybatis.mapper.common.Mapper;

public interface StorageMapper extends Mapper<Storage> {

    /**
     * 扣减库存
     * @param productId 商品ID
     * @param count 扣减数量
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}