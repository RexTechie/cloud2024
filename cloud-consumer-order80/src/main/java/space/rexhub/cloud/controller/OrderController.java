package space.rexhub.cloud.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import space.rexhub.cloud.entity.PayDTO;
import space.rexhub.cloud.resp.R;

import java.util.List;

/**
 * Description: 订单服务
 *
 * @author Rex
 * @date 2025-05-23
 */
@Tag(name="订单服务", description="订单服务")
@RestController
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://cloud-payment-service/pay";

    /**
     * 创建支付订单
     * @param payDTO 订单信息
     * @return 订单结果
     */
    @PostMapping(value = "consumer/pay/add")
    @Operation(summary = "创建支付订单", description = "创建支付订单")
    public R addOrder(@RequestBody PayDTO payDTO){
        ResponseEntity<R> resp = restTemplate.postForEntity(
                PAYMENT_URL + "/add",
                payDTO,
                R.class);
        return resp.getBody();
    }

    /**
     * 删除支付订单
     * @param id 订单ID
     * @return 删除结果
     */
    @DeleteMapping(value = "consumer/pay/del/{id}")
    @Operation(summary = "删除支付订单", description = "删除支付订单")
    public R<Void> deleteOrder(@PathVariable(value = "id") Integer id){
        restTemplate.delete(PAYMENT_URL + "/del/" + id);
        return R.success();
    }

    /**
     * 修改支付订单
     * @param payDTO 订单信息
     * @return 修改结果
     */
    @PutMapping(value = "consumer/pay/update")
    @Operation(summary = "修改支付订单", description = "修改支付订单")
    public R<Void> updateOrder(@RequestBody PayDTO payDTO){
        restTemplate.put(PAYMENT_URL + "/update", payDTO);
        return R.success();
    }

    /**
     * 查询支付订单
     * @param id 订单ID
     * @return 订单信息
     */
    @GetMapping(value = "consumer/pay/get/{id}")
    @Operation(summary = "查询支付订单", description = "查询支付订单")
    public R getPayInfo(@PathVariable("id") Integer id){
        ResponseEntity<R> res = restTemplate.getForEntity(PAYMENT_URL + "/get/" + id, R.class);
        return res.getBody();
    }

    /**
     * 查询所有支付订单
     * @return 所有支付订单
     */
    @GetMapping(value = "consumer/pay/getAll")
    @Operation(summary = "查询所有支付订单", description = "查询所有支付订单")
    public R getPayList(){
        ResponseEntity<R> res = restTemplate.getForEntity(PAYMENT_URL + "/getAll", R.class);
        return res.getBody();
    }

    /**
     * 获取Consul注册中心的服务信息
     * @return 服务信息
     */
    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul(){
        return restTemplate.getForObject(PAYMENT_URL + "/get/info", String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 获取服务发现信息
     * @return 服务实例信息
     */
    @GetMapping(value = "/consumer/discovery")
    public String discovery(){
        List<String> services = discoveryClient.getServices();
        for(String service : services){
            System.out.println(service);
        }
        System.out.println("----------------------------");
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for(ServiceInstance instance : instances){
            System.out.println(instance.getServiceId() + "\t" +
                    instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri());
        }
        return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
    }
}
