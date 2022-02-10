package cn.vt.proxy.cglib;

import cn.vt.proxy.jdk.Human;
import org.springframework.context.annotation.Bean;

// 被代理类
public class TestConfigurationSuperMan implements Human {

    @Override
    public void info() {
        fly();
        System.out.println("我是超人！我怕谁！");
    }

    @Bean
    @Override
    public void fly() {
        System.out.println("I believe I can fly!");
    }
}