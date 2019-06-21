package cn.kang.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 发布者
 */
@Component
public class Publisher extends Thread {
    @Autowired
    private final JedisPool jedisPool;

    public Publisher(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Async
    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Jedis jedis = jedisPool.getResource();                      //连接池中取出一个连接
        while (true) {
            String line = null;
            try {
                line = bufferedReader.readLine();
                if (!"quit".equals(line)) {
                    jedis.publish("myChannel", line);      //从 myChannel 的频道上推送消息
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
