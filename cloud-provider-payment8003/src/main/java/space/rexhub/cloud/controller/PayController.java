package space.rexhub.cloud.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import space.rexhub.cloud.entities.Pay;
import space.rexhub.cloud.entity.PayDTO;
import space.rexhub.cloud.resp.R;
import space.rexhub.cloud.service.PayService;

import java.util.List;

/**
 * Description: 支付控制层
 *
 * @author Rex
 * @date 2025-05-23
 */
@Tag(name="支付微服务模块", description = "支付CRUD")
@RestController
@Slf4j
public class PayController {

    @Resource
    private PayService payService;

    /**
     * 插入支付流水
     * @param pay 支付流水
     * @return 成功插入支付流水，返回值
     */
    @PostMapping("pay/add")
    @Operation(summary = "插入支付流水", description = "新增支付流水方法")
    public R<String> addPay(@RequestBody Pay pay) {
        log.info(pay.toString());
        int i = payService.add(pay);
        return R.success("成功插入记录，返回值：" + i);
    }

    /**
     * 删除支付流水
     * @param id 支付流水ID
     * @return 删除结果
     */
    @DeleteMapping("pay/del/{id}")
    @Operation(summary = "删除支付流水", description = "删除支付流水方法")
    public R<Integer> deletePay(@PathVariable("id") Integer id) {
        return R.success(payService.delete(id));
    }

    /**
     * 更新支付流水
     * @param payDTO 支付流水
     * @return 支付流水
     */
    @PutMapping("pay/update")
    @Operation(summary = "更新支付流水", description = "更新支付流水方法")
    public R<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return R.success("成功修改记录，返回值：" + i);
    }

    /**
     * 查询支付流水
     * @param id 支付流水ID
     * @return 支付流水
     */
    @GetMapping("pay/get/{id}")
    @Operation(summary = "查询支付流水", description = "查询支付流水方法")
    public R<Pay> getById(@PathVariable("id") Integer id) {
        if(id < 0) throw new RuntimeException("查询ID不能为负数");
        return R.success(payService.getById(id));
    }

    /**
     * 查询所有支付流水
     * @return 支付流水列表
     */
    @GetMapping("pay/getAll")
    @Operation(summary = "查询所有支付流水", description = "查询所有支付流水方法")
    public R<List<Pay>> getAll() {
        return R.success(payService.getAll());
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "pay/get/info")
    public String getInfoByConsul(@Value("${rexhub.info}") String rehubInfo) {
        return "Consul服务端口：" + port + "，" + rehubInfo;
    }
}
