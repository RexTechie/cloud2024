package space.rexhub.cloud.service;

import space.rexhub.cloud.entities.Pay;

import java.util.List;

/**
 * Description: 支付服务类
 *
 * @author Rex
 * @date 2025-05-23
 */
public interface PayService {
    /**
     * 新增支付记录
     *
     * @param pay 支付记录
     * @return 影响行数
     */
    int add(Pay pay);

    /**
     * 删除支付记录
     *
     * @param id 主键ID
     * @return 影响行数
     */
    int delete(Integer id);

    /**
     * 修改支付记录
     *
     * @param pay 支付记录
     * @return 影响行数
     */
    int update(Pay pay);

    /**
     * 查询支付记录
     *
     * @param id 主键ID
     * @return 支付记录
     */
    Pay getById(Integer id);

    /**
     * 查询所有支付记录
     *
     * @return 支付记录列表
     */
    List<Pay> getAll();
}
