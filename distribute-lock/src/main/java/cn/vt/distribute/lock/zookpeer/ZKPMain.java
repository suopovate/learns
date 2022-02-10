package cn.vt.distribute.lock.zookpeer;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.Console;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vate
 */
public class ZKPMain {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 3; i++) {
            executorService.submit(
                    () -> {
                        try {
                            ZookeeperHandler zkh = new ZookeeperHandler("127.0.0.1:2181");

                            String threadName = Thread.currentThread().getName();
                            StopWatch stopWatch = new StopWatch();
                            stopWatch.start();
                            Console.log("{}- 开始尝试获取锁.", threadName);

                            zkh.lockSimpleSync("测试", "123");
                            stopWatch.stop();
                            Console.log("{}- 锁获取成功,花费时间{}s", threadName, stopWatch.getTotalTimeSeconds());

                            zkh.unlockSimple("测试", "123");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
    }
}