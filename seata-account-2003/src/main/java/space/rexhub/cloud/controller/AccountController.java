package space.rexhub.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.rexhub.cloud.resp.R;
import space.rexhub.cloud.service.AccountService;

/**
 * Description: AccountController
 *
 * @author Rex
 * @date 2025-06-14
 */
@RestController
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 扣减账户余额
     * @param userId 用户ID
     * @param money 扣减金额
     * @return
     */
    @PostMapping("/account/decrease")
    public R<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money){
        accountService.decrease(userId, money);
        return R.success("扣减账户余额成功！");
    }
}
