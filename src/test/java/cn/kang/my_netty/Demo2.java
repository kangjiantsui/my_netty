package cn.kang.my_netty;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Demo2 {
    public static class AThread implements Runnable {
        public static Map<Integer, Integer> map = new HashMap<>();
        public static Random random = new Random();

        @Override
        public void run() {
            while (map.size() < 100) {
                map.put(random.nextInt(), map.size());
                System.out.println(Thread.currentThread().getName() + ":    " + map.size());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new AThread());
        Thread thread2 = new Thread(new AThread());
        thread1.start();
        thread2.start();
        Thread.sleep(1);

        AThread.map.values().stream().sorted().forEach(System.out::println);
    }
}
