package cn.cast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;



/**
 * @author ：Polarbear
 * @date ：Created 2023/2/23 22:35
 * @description：Feign 的代码优化
 */

public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
