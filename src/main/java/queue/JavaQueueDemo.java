package queue;

import java.io.Console;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author vate
 */
public class JavaQueueDemo {

    static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);

    public static void main(String[] args) {

//        linkedBlockingQueue.add(new Object());
//        linkedBlockingQueue.offer(new Object());

        consumer();
        producer();
    }

    public static void consumer() {
        int n = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            executorService.execute(() -> {
                while (true) {
                    try {
                        linkedBlockingQueue.put(new Object());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {}
                }
            });
        }
    }

    public static void producer() {
        int n = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        for (int i = 0; i < n; i++) {
            executorService.execute(() -> {
                int count = 0;
                while (true) {
                    try {
                        System.out.println(String.format("第 %s 个 对象 : %s", ++count, linkedBlockingQueue.take()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
