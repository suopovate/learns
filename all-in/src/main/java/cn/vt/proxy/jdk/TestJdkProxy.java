package cn.vt.proxy.jdk;

public class TestJdkProxy {
    public static void main(String[] args) {
        SuperMan beProxy = new SuperMan();
        MyInvocationHandler<Human> invocationHandler = new MyInvocationHandler<>();
        Human proxyObj = invocationHandler.bind(beProxy);
        proxyObj.fly();
        proxyObj.info();
    }
}
