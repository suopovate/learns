package vt.reactor;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Consumer;

public class VtSubscriber<T> implements Subscriber<T> {

    String id = IdUtil.fastSimpleUUID();

    Consumer<T> consumer;

    Subscription subscription;

    public VtSubscriber(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onSubscribe(Subscription s) {
        Console.log("开启订阅-订阅者: {}", id);
        subscription = s;
        subscription.request(1);
    }

    @Override
    public void onNext(T t) {
        try {
            consumer.accept(t);
        }catch (Throwable throwable){
            onError(throwable);
            onComplete();
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        Console.error(t);
    }

    @Override
    public void onComplete() {
        Console.log("订阅完成,订阅者id: {}", id);
    }
}
