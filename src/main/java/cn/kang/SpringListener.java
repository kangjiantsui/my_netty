package cn.kang;

import cn.kang.netty.websocket.ProtobufServer;
import cn.kang.netty.websocket.WebSocketServer;
import cn.kang.redis.PubSubDemo;
import cn.kang.redis.Publisher;
import cn.kang.redis.SubThread;
import cn.kang.socket.ScoketClient;
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
    private WebSocketServer webSocketServer;            //处理text格式ws请求
    @Autowired
    private ProtobufServer protobufServer;              //处理proto格式ws请求
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {//以免onApplicationEvent方法执行两次,root application context没有parent
            try {
                subThread.start();
                publisher.start();
                webSocketServer.run(8080);
                protobufServer.run(8899);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
