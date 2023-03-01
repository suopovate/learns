package cn.vt.reactive;


import rx.Observable;
import rx.Subscriber;

import static java.lang.String.valueOf;

public class Learn {
    public static void main(String[] args) {
        Observable.just(1,2)
            .lift(new MyOperator())
            .subscribe(System.out::println);
    }

    public static class MyOperator implements Observable.Operator<String,Integer> {

        @Override
        public Subscriber<? super Integer> call(Subscriber<? super String> subscriber) {
            return new Subscriber<Integer>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    System.err.println(e);
                }

                @Override
                public void onNext(Integer s) {
                    try {
                        subscriber.onNext(s + "v");
                    } catch (Throwable t) {
                        onError(t);
                    }
                }
            };
        }
    }
}
