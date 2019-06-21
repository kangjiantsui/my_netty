package cn.kang.netty.websocket;

import cn.kang.Task;
import cn.kang.redis.Publisher;
import cn.kang.redis.SubThread;
import cn.kang.redis.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
public class NettybootServerInitConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private Task task;
    @Autowired
    private SubThread subThread;
    @Autowired
    private Publisher publisher;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {//以免onApplicationEvent方法执行两次,root application context没有parent
            try {
                /*WebSocketServer.getInstance().run(8080);
                SubThread subThread = new SubThread(jedisPool);     //订阅者
                subThread.start();
                Publisher publisher = new Publisher(jedisPool);     //发布者
                publisher.start();*/
/*                task.doTaskOne();
                task.doTaskTwo();
                task.doTaskThree();*/
                subThread.run();
                publisher.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
