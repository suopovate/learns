package cn.vt.thread.safe;

import cn.hutool.core.lang.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author vate
 */
public class ThreadSafeObjectTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HashMap<SameKey, String> unSafeMap = new HashMap<>();
        ConcurrentHashMap<SameKey, String> safeMap = new ConcurrentHashMap<>();
        ForkJoinPool pool = new ForkJoinPool(4);
        pool.submit(() ->
                Stream.generate(SameKey::new)
                        .limit(5).parallel()
                        .forEach(sameKey -> {
//                            String s = String.valueOf(Math.random());
                            unSafeMap.put(sameKey, "123");
                            safeMap.put(sameKey, "123");
                        })
        ).get();

        System.out.println(String.format("unSafeMap element count: %s", unSafeMap.size()));
        System.out.println(String.format("safeMap element count: %s", safeMap.size()));

        ExecutorService executorService = Executors.newFixedThreadPool(1);

    }
}