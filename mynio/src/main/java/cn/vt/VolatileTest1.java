package cn.vt;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class VolatileTest1 {
    /**
     * 如果没有 volatile 关键字，m end 是不会结束的
     */
    boolean running = true;

    public void m(VolatileTest2 v2) {
        System.out.println("m start");
        Random random=new Random();
        random.nextInt();
        while (running) {
            String s = "123";
            // lock add v2.a +++
        }
        System.out.println(v2.a);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest1 volatileTest1 = new VolatileTest1();
        VolatileTest2 volatileTest2 = new VolatileTest2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileTest1.m(volatileTest2);
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
//        synchronized (volatileTest1) {
            volatileTest1.running = false;
//        }
    }
}