package vt.reactor;

import cn.hutool.core.lang.Console;
import reactor.core.publisher.Flux;
import reactor.util.context.Context;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.*;
import java.util.stream.StreamSupport;

public class Stream<T> {

    public static class Parent {}

    public static class Child extends Parent {}

//    public static void main(String[] args) {
//        //        new ArrayList<String>().stream().map(s -> s);
//        Flux<String> flux = Flux.generate(
//            () -> 0, // 初始state值
//            (state, sink) -> {
//                sink.next("3 x " + state + " = " + 3 * state); // 产生数据是同步的，每次产生一个数据
//                if (state == 10) {
//                    sink.complete();
//                }
//                return state + 1; // 改变状态
//            },
//            (state) -> System.out.println("state: " + state)
//        ); // 最后状态值
//        // 订阅时触发requset->sink.next顺序产生数据
//        // 生产一个数据消费一个
//        flux.log().subscribe(System.out::println);
//    }

    public static void main(String[] args) {
        ArrayList<String> ss = new ArrayList<>();
        ss.add("1");
        ss.add("2");
        StreamSupport.stream(ss.spliterator(), false)
            .map(s -> s + "v")
            .filter(s -> s.contains("1"))
            .forEach(System.out::println);

        Integer i = 123;
        Integer i1 = 123;
        Integer i2 = 1234;
        Integer i3 = 1234;
        Integer i4 = 1234;

    }
}
