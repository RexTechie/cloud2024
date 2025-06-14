package space.rexhub.cloud.service;

/**
 * Description: AccountService
 *
 * @author Rex
 * @date 2025-06-14
 */
public interface AccountService {
    /**
     * 扣减账户余额
     * @param userId 用户ID
     * @param money 扣减金额
     */
    void decrease(Long userId, Long money);
}
