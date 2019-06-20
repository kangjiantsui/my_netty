package cn.kang.netty.websocket;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettybootServerInitConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {//以免onApplicationEvent方法执行两次,root application context没有parent
            try {
                WebSocketServer.getInstance().run(8080);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
