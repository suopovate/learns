package cn.vt.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    // CGLib需要代理的目标对象
    private Object targetObject;

    public Object bind(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        // 设置父类--可以是类或者接口
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        // 返回代理对象
        return proxyObj;
    }

    public Object bind(Class clazz) {
        Enhancer enhancer = new Enhancer();
        // 设置父类--可以是类或者接口
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        // 返回代理对象
        return proxyObj;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        if (targetObject!=null){
            return method.invoke(targetObject,args);
        }else {
            // 这个分支是为了研究Spring中@Configuration 和 @Bean 能够配合的原理
            // 我们如何才能让super类中的info方法得以执行 同时也能应用到我们的增强呢？

            // 在这里 我们在TestConfigurationSuperMan中 把fly方法注解上@Bean
            // 同时在这里检测 针对Bean方法 做增强
            if (method.getAnnotation(Bean.class) != null){
                System.out.println("单独对fly方法进行增强");
            }

            // 这一句 非常重要
            // 我们假设 我们直接对@Configuration标注的类
            // 生成了一个代理类，这个代理类的MethodInvocation就是我们现在这个
            // 然后呢 我们拦截的时候 针对@Bean注解的类 额外做处理(上面那个if语句)，处理完就调用正常的被代理类的代码
            // 那我们如何调用被代理类的那个方法的代码呢？因为我们现在是代理类，相当于是被代理类的子类，所以我们要调用的是父类的方法
            // 刚好 cglib提供了这样的能力 我们通过下面的调用，就可以实现调用的是父类的方法了
            return methodProxy.invokeSuper(proxy,args);
        }
    }
}