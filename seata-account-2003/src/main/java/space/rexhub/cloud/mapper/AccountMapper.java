package space.rexhub.cloud.mapper;

import org.apache.ibatis.annotations.Param;
import space.rexhub.cloud.entities.Account;
import tk.mybatis.mapper.common.Mapper;

public interface AccountMapper extends Mapper<Account> {
    /**
     * 扣减账户余额
     *
     * @param userId 用户ID
     * @param money  扣减金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}