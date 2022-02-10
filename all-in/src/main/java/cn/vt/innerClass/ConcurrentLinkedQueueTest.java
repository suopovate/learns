package cn.vt.innerClass;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author vate
 */
public class ConcurrentLinkedQueueTest {
    public static void testOutOfMemory(){
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        String a="a";
        String b="b";
        queue.offer(a);
        for(int i=0;;i++){
            if(i % 1024 == 0) {
                System.out.println("i = "+i);
            }
            queue.offer(b);
            queue.remove(b);

        }
    }

    public static void main(String[] args) {
        testOutOfMemory();
    }
}
