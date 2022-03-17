package vt.cn.sb;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestInjectSafeComponent implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    ThreadSafeComponent threadSafeComponent;

    public static void main(String[] args) {

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        new Thread(() -> {
            threadSafeComponent.printCurrentThread();
        }).start();
        new Thread(() -> {
            threadSafeComponent.printCurrentThread();
        }).start();
        new Thread(() -> {
            threadSafeComponent.printCurrentThread();
        }).start();
    }
}
