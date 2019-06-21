package cn.kang.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class PubSubDemo {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "114.116.175.209", 6379);

        System.out.println(String.format("redis pool is starting,redis ip %s, redis port %d,", "114.116.175.209", 6379));

        SubThread subThread = new SubThread(jedisPool);     //订阅者
        subThread.start();
        Publisher publisher = new Publisher(jedisPool);     //发布者
        publisher.start();
    }
}
