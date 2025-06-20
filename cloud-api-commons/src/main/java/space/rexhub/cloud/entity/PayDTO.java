package space.rexhub.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description: 一般而言，调用者不应该获悉服务提供者的entity资源并知道表结构关系，所以服务提供方给出的接口文档都都应成为DTO
 *
 * @author Rex
 * @date 2025-05-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO implements Serializable {

    private Integer id;
    //支付流水号
    private String payNo;
    //订单流水号
    private String orderNo;
    //用户账号ID
    private Integer userId;
    //交易金额
    private BigDecimal amount;
}
