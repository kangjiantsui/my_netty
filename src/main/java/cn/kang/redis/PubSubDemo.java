package cn.kang.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class PubSubDemo {
    @Autowired
    private SubThread subThread;
    @Autowired
    private Publisher publisher;

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "114.116.175.209", 6379);

        System.out.println(String.format("redis pool is starting,redis ip %s, redis port %d,", "114.116.175.209", 6379));

        SubThread subThread = new SubThread(jedisPool);     //订阅者
        subThread.start();
        Publisher publisher = new Publisher(jedisPool);     //发布者
        publisher.start();
    }

    @Async
    public void run() {
        subThread.start();
        publisher.start();
    }
}
