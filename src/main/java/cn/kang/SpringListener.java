package cn.kang;

import cn.kang.Task;
import cn.kang.netty.websocket.WebSocketServer;
import cn.kang.redis.PubSubDemo;
import cn.kang.redis.Publisher;
import cn.kang.redis.SubThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
public class SpringListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private Task task;
    @Autowired
    private PubSubDemo pubSubDemo;
    @Autowired
    private SubThread subThread;
    @Autowired
    private Publisher publisher;
    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {//以免onApplicationEvent方法执行两次,root application context没有parent
            try {
                subThread.start();
                publisher.start();
                webSocketServer.run(8080);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
