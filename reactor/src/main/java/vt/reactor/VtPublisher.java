package vt.reactor;

import cn.hutool.core.thread.NamedThreadFactory;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class VtPublisher<T> implements Publisher<T> {

    Queue<T> queue = new ConcurrentLinkedQueue<>();

    ExecutorService executorService = Executors.newFixedThreadPool(8,new NamedThreadFactory("sub",true));

    @Override
    public void subscribe(Subscriber s) {
        VtSubscription subscription = new VtSubscription(s);
        executorService.execute(() -> s.onSubscribe(subscription));
    }

    public void publish(T t) {
        queue.offer(t);
    }

    public class VtSubscription implements Subscription {

        Subscriber<T> subscriber;

        public VtSubscription(Subscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            assert n > 0;
            for (int i = 0; i < n; i++) {
                executorService.execute(() -> Optional.ofNullable(VtPublisher.this.queue.poll()).ifPresent(value -> subscriber.onNext(value)));
            }
        }

        @Override
        public void cancel() {

        }
    }
}
