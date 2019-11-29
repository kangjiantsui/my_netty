package cn.kang.my_netty;

public class TestEvent {
    private final int message;

    TestEvent(int message) {
        this.message = message;
        System.out.println("event person:" + message);
    }

    public int getMessage() {
        return message;
    }
}
