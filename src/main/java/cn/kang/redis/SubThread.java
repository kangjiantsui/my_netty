package cn.kang.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class SubThread extends Thread {
    @Autowired
    private final JedisPool jedisPool;
    private final Subscriber subscriber = new Subscriber();

    public SubThread(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Async
    @Override
    public void run() {
        String channel = "myChannel";
        System.out.println(String.format("subscribe redis, channel %s,thread will be blocked", channel));
        try (Jedis jedis = jedisPool.getResource()) {
            //取出一个连接
            jedis.subscribe(subscriber, channel);   //通过subscribe的api去订阅,入参是订阅者和频道名
        } catch (Exception e) {
            System.out.println(String.format("subscribe channel error, %s", e));
        }
    }
}
