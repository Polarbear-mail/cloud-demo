package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
// 热更新配置加载
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

//     //更改热更新
//    @Value("${pattern.dateformat}")
//    private String dateformat;
    @Autowired
    private PatternProperties properties;

    //获取热更新数据
    @GetMapping("prop")
    public PatternProperties properties(){
        return properties;
    }

    //测试热更新 时间
    @GetMapping("now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat()));
    }




    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id, @RequestHeader(value= "Truth",required = false) String  truth) {
        System.out.println("truth: "+truth);
        return userService.queryById(id);
    }
}
