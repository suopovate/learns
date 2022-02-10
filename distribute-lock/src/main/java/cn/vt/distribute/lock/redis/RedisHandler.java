package cn.vt.distribute.lock.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.sync.RedisCommands;

/**
 * @author vate
 */
public class RedisHandler {

    private static final String LOCK_LUA_SCRIPT =
//            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            "if redis.call('exists', KEYS[1]) == 0 then return redis.call('setex', KEYS[1], 30, ARGV[1]) == 'OK' else return 0 end";

    private static final String RELEASE_LOCK_LUA_SCRIPT =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    public RedisClient client;

    public static RedisHandler createRh(String host, int port, String password) {
        RedisHandler redisHandler = new RedisHandler();
        RedisURI redisURI = RedisURI.create(host, port);
        redisURI.setPassword(password);
        redisHandler.client = RedisClient.create(redisURI);
        return redisHandler;
    }

    public void lockSimple(String key, String clientId) {
        RedisCommands<String, String> commands = client.connect().sync();
//        commands.eval("",, )
//        if (commands.exists(key).intValue() < 0) {
//            commands.setex(key, 30, clientId);
//        }

    }
}
