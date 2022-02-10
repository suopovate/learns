package cn.vt.proxy.cglib;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public Object obj1() {
        System.out.println(obj2());
        System.out.println(obj2());
        System.out.println(obj2());
        return new Object();
    }
    @Bean
    public Object obj2() {
        return new Object();
    }
}