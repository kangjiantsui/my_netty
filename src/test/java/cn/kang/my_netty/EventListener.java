package cn.kang.my_netty;

import com.google.common.eventbus.Subscribe;

public class EventListener {
    private int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:" + lastMessage);
    }

    int getLastMessage() {
        return lastMessage;
    }
}
