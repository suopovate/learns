package vt.cn.sb;

import cn.hutool.core.lang.Console;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName= "ThreadScope",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ThreadSafeComponent {

    Thread cThread;

    public ThreadSafeComponent() {
        cThread = Thread.currentThread();
    }

    public void printCurrentThread() {
        Console.log(Thread.currentThread().getName());
    }

    public boolean isSameThread(Thread t) {
        return cThread == t;
    }
}
