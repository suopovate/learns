import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: rocky
 * @Date: Created in 2018/5/12.
 */

public class ZookeeperTestConnection implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();

    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive the event:"+event);

        switch (event.getType()){
            case NodeDataChanged:{
                try {
                    System.out.println("配置已修改，新值为：" + new String(zooKeeper.getData(event.getPath(), true, stat)));
                } catch (Exception e) {
                }
            }
        }
    }
    public static final String ADDRESS = "47.107.56.246:2181";

    private static final String PREFIX = "/vate-sync-create-0000000019";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        zooKeeper = new ZooKeeper(ADDRESS, 5000, new ZookeeperTestConnection());
        System.out.println(zooKeeper.getState());
        System.out.println(new String(zooKeeper.getData(PREFIX, true, stat)));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("zookeeper session established");
    }
}