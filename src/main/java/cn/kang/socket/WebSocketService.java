package cn.kang.socket;


public interface WebSocketService {

    /**
     * 群发
     */
    void groupSending();

    /**
     * 指定发送
     */
    void appointSending(String name,String message);
}
