package cn.vt.distribute.lock.zookpeer;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Console;
import org.apache.zookeeper.*;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.apache.zookeeper.Watcher.Event.EventType.NodeDeleted;

/**
 * @author vate
 */
public class ZookeeperHandler {

    public static final String LOCK_PATH = "/vate/locks";

    public ZooKeeper zooKeeper;

    public ZookeeperHandler(String connectString) throws IOException, InterruptedException, KeeperException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(connectString, 1000 * 60, event -> {
            if (KeeperState.SyncConnected == event.getState()) {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        if (Objects.isNull(zooKeeper.exists("/vate", false))) {
            createNode("/vate", "", CreateMode.PERSISTENT);
        }
        if (Objects.isNull(zooKeeper.exists(LOCK_PATH, false))) {
            createNode(LOCK_PATH, "", CreateMode.PERSISTENT);
        }
    }

    public String createNode(String path, String data, CreateMode createMode) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, createMode);
    }

    public boolean deleteNode(String path) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, false);
        if (stat != null) {
            zooKeeper.delete(path, stat.getVersion());
            return true;
        }
        return false;
    }

    public boolean updateNode(String path, String data) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, false);
        if (stat != null) {
            zooKeeper.setData(path, data.getBytes(), stat.getVersion());
            return true;
        }
        return false;
    }

    public void registerListener(Watcher watcher) {
        zooKeeper.register(watcher);
    }

    public boolean lockSimple(String key, String clientId) {

        Assert.isFalse(key.startsWith("/"), "The key can't be start with /");
        Assert.notEmpty(clientId, "The clientId can't be empty");
        String path = LOCK_PATH + "/" + key;

        try {
            createNode(path, clientId, CreateMode.EPHEMERAL);
            return true;
        } catch (KeeperException e) {
        } catch (InterruptedException e) {
        }

        return false;
    }

    public boolean lockSimpleSync(String key, String clientId) {
        return lockSimpleSync(key, clientId, 0, null);
    }

    public boolean lockSimpleSync(String key, String clientId, long timeout, TimeUnit timeUnit) {

        Assert.isFalse(key.startsWith("/"), "The key can't be start with /");
        Assert.notEmpty(clientId, "The clientId can't be empty");
        String path = LOCK_PATH + "/" + key;

        final Exception[] lockExceptions = {null};

        try {
            createNode(path, clientId, CreateMode.EPHEMERAL);
            return true;
        } catch (KeeperException ke) {
            if (Code.NODEEXISTS == ke.code()) {
                // register callback and wait
                CountDownLatch countDownLatch = new CountDownLatch(1);
                try {
                    zooKeeper.exists(path, new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            if (NodeDeleted == event.getType()) {
                                countDownLatch.countDown();
                            } else {
                                // re-register watch
                                try {
                                    zooKeeper.exists(path, this);
                                } catch (Exception e) {
                                    // re-register failed notify the caller
                                    lockExceptions[0] = e;
                                    countDownLatch.countDown();
                                }
                            }
                        }
                    });

                    Console.log("lock exists,wait for lock release.");

                    if (timeout > 0) {
                        if (!countDownLatch.await(timeout, timeUnit)) {
                            return false;
                        }
                    } else {
                        countDownLatch.await();
                    }
                    if (lockExceptions[0] == null) {
                        lockSimpleSync(key, clientId, timeout, timeUnit);
                    }
                } catch (Exception e) {
                    lockExceptions[0] = e;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (lockExceptions[0] != null) {
            throw new RuntimeException(lockExceptions[0]);
        }

        return false;
    }

    public boolean unlockSimple(String key, String clientId) {

        Assert.isFalse(key.startsWith("/"), "The key can't be start with /");
        Assert.notEmpty(clientId, "The clientId can't be empty");

        String path = LOCK_PATH + "/" + key;

        try {
            Stat stat = new Stat();
            byte[] clientIdBytes = zooKeeper.getData(path, false, stat);
            if (clientIdBytes != null && clientId.equals(new String(clientIdBytes))) {
                zooKeeper.delete(path, stat.getVersion());
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

//
//    private boolean isFirstLock(List<String> lockPaths, String lockPath) {
//        lockPaths.stream().mapToLong(s -> Long.parseLong(s.substring(s.lastIndexOf(ORIGIN_LOCK_NODE)))).sorted()
//    }
//
//    public boolean unLock(String key){
//
//    }
}
