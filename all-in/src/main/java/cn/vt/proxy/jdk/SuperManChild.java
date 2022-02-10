package cn.vt.proxy.jdk;

// 被代理类
public class SuperManChild extends SuperMan {

    @Override
    public void info() {
        System.out.println("我是超人儿子！我怕谁！");
    }

    @Override
    public void fly() {
        System.out.println("超人儿子 I believe I can fly!");
    }

    public static void main(String[] args) {
        SuperMan superMan = new SuperManChild();
        superMan.info();
    }
}