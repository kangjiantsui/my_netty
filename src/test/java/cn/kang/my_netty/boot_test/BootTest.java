package cn.kang.my_netty.boot_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@RunWith(SpringJUnit4ClassRunner.class)
public class BootTest {
    @Test
    public void demo1() throws IOException {
        long count = new BufferedReader(new FileReader(new ClassPathResource("aaa.txt").getFile(), UTF_8)).lines().count();
        System.out.println(count);
    }
}
