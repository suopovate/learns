package cn.vt.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler<T> implements InvocationHandler {

    private static final Class<?>[] constructorParams =
            { InvocationHandler.class };

    // 被代理类对象的声明
    T beProxy;

    // 动态的创建一个代理类的对象
    public T bind(T beProxy){
        this.beProxy = beProxy;
        return (T) Proxy.newProxyInstance(
                beProxy.getClass().getClassLoader(),
                beProxy.getClass().getInterfaces(),
                this);
    }

    // 动态的创建一个代理类的对象
    public T createByInterface(Class<T>... interfaces) {
        try {
            return (T) Proxy.getProxyClass(
                    MyInvocationHandler.class.getClassLoader(),
                    interfaces).getConstructor(constructorParams).newInstance(this);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("invocation 先吃");
        return method.invoke(beProxy, args);
    }
}