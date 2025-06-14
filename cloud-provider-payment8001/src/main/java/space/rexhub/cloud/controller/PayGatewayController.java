package space.rexhub.cloud.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.rexhub.cloud.entities.Pay;
import space.rexhub.cloud.resp.R;
import space.rexhub.cloud.service.PayService;

import java.util.Enumeration;

/**
 * Description: 支付网关控制器
 *
 * @author Rex
 * @date 2025-06-06
 */
@RestController
public class PayGatewayController {
    @Resource
    private PayService payService;

    @GetMapping(value = "/pay/gateway/get/{id}")
    public R<Pay> getById(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        return R.success(pay);
    }

    @GetMapping(value = "/pay/gateway/info")
    public R<String> getGatewayInfo() {
        // 模拟网关信息
        String gatewayInfo = "支付网关服务运行正常: " + IdUtil.simpleUUID();
        return R.success(gatewayInfo);
    }

    @GetMapping(value = "/pay/gateway/filter")
    public R<String> getGatewayFilter(HttpServletRequest request) {
        String result = "";
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println("Header Name: " + headName + ", Header Value: " + headValue);
            if (headName.equalsIgnoreCase("X-Request-rex1") ||
                headName.equalsIgnoreCase("X-Request-rex2")
            ) {
                result = result + headName + ": " + headValue + "; ";
            }
        }

        System.out.println("==========================================");
        String customerId = request.getParameter("customerId");
        System.out.println("Customer ID: " + customerId);
        System.out.println("==========================================");
        String customerName = request.getParameter("customerName");
        System.out.println("Customer Name: " + customerName);
        System.out.println("==========================================");
        return R.success("getGatewayFilter 过滤器测试：" + result + DateUtil.now());
    }
}
