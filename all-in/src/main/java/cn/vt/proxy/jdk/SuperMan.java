package cn.vt.proxy.jdk;

// 被代理类
public class SuperMan implements Human {

    @Override
    public void info() {
        fly();
        System.out.println("我是超人！我怕谁！");
    }

    @Override
    public void fly() {
        System.out.println("I believe I can fly!");
    }
}