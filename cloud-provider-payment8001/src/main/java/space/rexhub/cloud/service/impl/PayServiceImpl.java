package space.rexhub.cloud.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import space.rexhub.cloud.entities.Pay;
import space.rexhub.cloud.mapper.PayMapper;
import space.rexhub.cloud.service.PayService;

import java.util.List;

/**
 * Description: 支付服务实现类
 *
 * @author Rex
 * @date 2025-05-23
 */
@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;

    /**
     * 新增支付记录
     *
     * @param pay 支付记录
     * @return 影响行数
     */
    @Override
    public int add(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    /**
     * 删除支付记录
     *
     * @param id 主键ID
     * @return 影响行数
     */
    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改支付记录
     *
     * @param pay 支付记录
     * @return 影响行数
     */
    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    /**
     * 查询支付记录
     *
     * @param id 主键ID
     * @return 支付记录
     */
    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有支付记录
     *
     * @return 支付记录列表
     */
    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
