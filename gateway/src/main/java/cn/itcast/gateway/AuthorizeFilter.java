package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ：Polarbear
 * @date ：Created 2022/12/6 9:50
 * @description：
 */
@Component
@Order(-1)    //越小优先级越高
public class AuthorizeFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 全局过滤器
        //1.  获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();

        //2. 获取参数中的authorzation 参数
        String auth = params.getFirst("authorization");

        //3. 判断参数值是否等于  admin
        if ("admin".equals(auth)){
            //4. 是，放行
            return chain.filter(exchange);
        }

        //5. 否，拦截

        //5.1  设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
         return exchange.getResponse().setComplete();
    }
}
