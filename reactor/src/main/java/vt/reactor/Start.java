package vt.reactor;

import cn.hutool.core.thread.ThreadUtil;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Start {
    public static void main(String[] args) {
        VtPublisher<String> vtPublisher = new VtPublisher<>();
        vtPublisher.subscribe(new VtSubscriber<String>(System.out::println));
        vtPublisher.publish("123");
        vtPublisher.publish("123");
        vtPublisher.publish("123");
        vtPublisher.publish("123");
        ThreadUtil.sleep(100);

//        Flux.just("").map(s -> )

        Stream.of("1").map(s -> s).collect(Collectors.toList());
        new ArrayList<>().stream();
    }
}
