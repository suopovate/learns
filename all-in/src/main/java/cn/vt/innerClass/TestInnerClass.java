package cn.vt.innerClass;

import java.io.Console;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @author vate
 */
public class TestInnerClass {

    public static void main(String[] args) {
        ArrayList<String> innerL1 = new ArrayList<String>() {{
            add("1");
        }};
        ArrayList<String> innerL2 = new ArrayList<String>() {{
            add("2");
        }};
        for (int i = 0; i < 1000; i++) {
            ArrayList<String> innerLn = new ArrayList<String>() {{
                add("n");
            }};
        }
        Runnable runnable = new Runnable() {
            {
                System.out.println("init runnable object");
            }
            @Override
            public void run() {
                System.out.println("running runnable object");
            }
        };
        Consumer consumer = o -> {
            System.out.println(o);
        };
        new ArrayList<>().parallelStream();
    }

}
