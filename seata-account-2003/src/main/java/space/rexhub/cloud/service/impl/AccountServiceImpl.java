package space.rexhub.cloud.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.rexhub.cloud.mapper.AccountMapper;
import space.rexhub.cloud.service.AccountService;

import java.util.concurrent.TimeUnit;

/**
 * Description: AccountServiceImpl
 *
 * @author Rex
 * @date 2025-06-14
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 扣减账户余额
     *
     * @param userId 用户ID
     * @param money  扣减金额
     */
    @Override
    public void decrease(Long userId, Long money) {
        log.info("-------> AccountServiceImpl.decrease 扣减账户余额开始");
        accountMapper.decrease(userId, money);
         // myTimeOut();
        // int age = 10 / 0;
        log.info("-------> AccountServiceImpl.decrease 扣减账户余额结束");
    }

    /**
     * 模拟超时异常，全局事务回滚
     */
    private static void myTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
