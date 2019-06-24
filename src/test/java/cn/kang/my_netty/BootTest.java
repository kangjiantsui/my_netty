package cn.kang.my_netty;

import cn.kang.redis.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BootTest {

    @Autowired
    private Publisher publisher;

    @Test
    public void demo1() {
        System.out.println("Go");
    }
}
