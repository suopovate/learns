package vt.cn.sb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationTest {
    public static class C1{}

    @Bean
    public Object nothing(){
        System.out.println(c1());
        System.out.println(c1());
        System.out.println(c1());
        return new Object();
    }

    @Bean
    public C1 c1(){
        return new C1();
    }
}
