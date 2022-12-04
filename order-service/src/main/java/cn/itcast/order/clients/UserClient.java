package cn.itcast.order.clients;

import cn.itcast.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ：Polarbear
 * @date ：Created 2022/12/4 0:22
 * @description：
 */

@FeignClient("userservice")
public interface UserClient {
    @GetMapping("/user/{id}")
    User findByid(@PathVariable("id") Long id);
}
