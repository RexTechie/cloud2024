package space.rexhub.cloud.mygateway;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Description: 需求说明，自定义配置会员等级userType，按照钻/金/银/铜等级进行路由转发。
 *
 * @author Rex
 * @date 2025-06-06
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    // 这个Config类就是我们的路由断言规则，重要
    @Validated
    public static class Config {
        @Setter
        @Getter
        @NotEmpty
        private String userType; // 会员等级，钻/金/银/铜
    }

    MyRoutePredicateFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }


    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
            if (userType == null) {
                return false; // 如果没有userType参数，直接返回false
            }
            if (userType.equalsIgnoreCase(config.getUserType())) {
                return true; // 如果userType匹配配置的值，返回true
            }
            return false;
        };
    }


}

