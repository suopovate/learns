package vt.learn.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author vate
 */
public class GclibTest {

    static class Parent {

        String pName;

        public void pHello(String name) {
            System.out.println(name + ":P Hello.");
        }

        public void pHello2() {
            System.out.println(pName + ":P Hello.");
        }

        public Parent(String pName) {
            this.pName = pName;
        }

        public void setpName(String pName) {
            this.pName = pName;
        }

        public String getpName() {
            return pName;
        }

        public String getFuck() {
            return "fuck";
        }
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/vate/softcache/workspace_java/mines/learns/dynamic-proxy/target/classes/vt/learn/proxy");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Parent.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
//            System.out.println();
//            method.invoke(obj,args1);
//            proxy.invoke(obj,args1);
            proxy.invokeSuper(obj,args1);
            return null;
        });
        Object parent = enhancer.create(new Class[]{String.class},new Object[]{"哦"});
        ((Parent) parent).pHello("好");
        System.out.println();
    }

}
