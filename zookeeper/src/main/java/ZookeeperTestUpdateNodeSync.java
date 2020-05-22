import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: rocky
 * @Date: Created in 2018/5/12.
 */
public class ZookeeperTestUpdateNodeSync implements Watcher {
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static final String ADDRESS = "47.107.56.246:2181";

    private static final String PREFIX = "/vate-sync-create-0000000019";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(ADDRESS, 5000, new ZookeeperTestUpdateNodeSync());
        System.out.println("state:"+zooKeeper.getState());
        zooKeeper.setData(PREFIX,"我改变了2".getBytes() ,-1 );
        zooKeeper.close();
    }

    @Override
    public void process(WatchedEvent event) {
        //连上了
        if(Event.KeeperState.SyncConnected == event.getState())
            countDownLatch.countDown();
    }
}