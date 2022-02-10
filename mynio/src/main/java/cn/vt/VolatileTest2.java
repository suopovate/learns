package cn.vt;

import java.util.concurrent.TimeUnit;

public class VolatileTest2 {
    /**
     * 如果没有 volatile 关键字，m end 是不会结束的
     */
    volatile int a = 1;
}