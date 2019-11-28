package cn.kang.bean;


import cn.kang.netty.websocket.ProtobufServer;
import cn.kang.netty.websocket.WebSocketServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 结束的时候执行
 */
@SuppressWarnings("RedundantThrows")
@Component
public class MyDisposableBean implements DisposableBean {

    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private ProtobufServer protobufServer;
    @Override
    public void destroy() throws Exception {
        System.out.println("结束");

    }

}
