package cn.kang.socket;


import cn.kang.common.protocol.PersonMessage;
import cn.kang.common.protocol.SglMsg;
import cn.kang.common.protocol.SglMsgType;
import cn.kang.common.protocol.UserMsg;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: websocket接口实现类
 */
@Component
public class ScoketClient implements WebSocketService{

    @Autowired
    private WebSocketClient webSocketClient;

    @Override
    public void groupSending() {
        // 这里我加了6666-- 是因为我在index.html页面中，要拆分用户编号和消息的标识，只是一个例子而已
        // 在index.html会随机生成用户编号，这里相当于模拟页面发送消息
        // 实际这样写就行了 webSocketClient.send(message)
        PersonMessage.Person pb = PersonMessage.Person.newBuilder().setId(20).setName("小王").build();

        UserMsg.UserRegReq ub = UserMsg.UserRegReq.newBuilder()
                .setChannel("ASDK")
                .setRid(10005)
                .setUid("nox5@vn")
                .setVersion("1.0.0.99523")
                .setCid(".1224018227.")
                .setBinaryVersion("1.0.0.99523")
                .setAppid("1224018227")
                .build();
        SglMsg.SglReqMsg sb = SglMsg.SglReqMsg.newBuilder()
                .setType(SglMsgType.ProtoMsgType.PB_TYPE_AUTHENTICATION)
                /*.setExtension(UserMsg.SglUserMsg.userRegReq, ub)*/.build();
        webSocketClient.send(sb.toByteArray());

    }

    @Override
    public void appointSending(String name, String message) {
        // 这里指定发送的规则由服务端决定参数格式
        webSocketClient.send("TOUSER"+name+";"+message);
    }
}
