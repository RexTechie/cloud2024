package space.rexhub.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * Description: 流量控制服务类
 *
 * @author Rex
 * @date 2025-06-09
 */
@Service
public class FlowLimitService {

    @SentinelResource(value = "common")
    public void common(){
        System.out.println("-------- FlowLimitService come in --------");
    }
}
