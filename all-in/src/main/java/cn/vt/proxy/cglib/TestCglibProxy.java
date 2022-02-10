package cn.vt.proxy.cglib;

import cn.vt.proxy.jdk.Human;
import cn.vt.proxy.jdk.SuperMan;
import net.sf.cglib.core.DebuggingClassWriter;

public class TestCglibProxy {

    public static void main(String[] args) {
        SuperMan man = new SuperMan();//创建一个被代理类的对象

        // 添加如下代码，获取代理类源文件
        String path = CGLibProxy.class.getResource(".").getPath();
        System.out.println(path);
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);

        CGLibProxy cgLibProxy = new CGLibProxy();
        Object proxyObj = cgLibProxy.bind(TestConfigurationSuperMan.class);//返回一个代理类的对象
        System.out.println(proxyObj.getClass());
        //class com.web.test.SuperMan$$EnhancerByCGLIB$$3be74240
        Human hu = (Human)proxyObj;
        hu.info();//通过代理类的对象调用重写的抽象方法

        System.out.println();

        hu.fly();
    }
}

