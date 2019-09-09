package cn.kang.my_netty;

import com.google.common.eventbus.EventBus;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Test
    public void demo1() {
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        System.out.println(now);
    }

    @Test
    public void demo2() {
        LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date from = Date.from(instant);
        System.out.println(from);
    }

    @Test
    public void testReceiveEvent() {
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:" + listener.getLastMessage());
    }

    @Test
    public void demo3() {
        byte[] encode = SafeEncoder.encode("nox2@vn");
        System.out.println(encode);
    }

    private static final Jedis jedis = new Jedis("114.116.175.209", 6379);


    @Test
    public void demo4() {
        jedis.rpush("1", "哈哈");
        jedis.rpush("1", "呵呵");
        jedis.rpush("1", "嘿嘿");
        jedis.rpush("1", "嘻嘻");

        List<String> lrange = jedis.lrange("1", 0, -1);
    }

    @Test
    public void demo5() {
        HashMap<String, String> map = new HashMap<>();
        map.put("10101", "哈哈");
        map.put("10102", "呵呵");
        jedis.hmset("map", map);

        List<String> map1 = jedis.hmget("map", "10101");
        System.out.println(map1);
    }

    @Test
    public void demo6() {
        jedis.hset("map", "10103", "呵呵哈哈asdasd");
        Set<String> map = jedis.hkeys("map");
        System.out.println(map);
        List<String> map1 = jedis.hvals("map");
        System.out.println(map1);
    }

    @Test
    public void demo7() {
        LocalDate birthday = LocalDate.of(1997, 10, 29);
        LocalDate now = LocalDate.now();
        long until = birthday.until(now, ChronoUnit.YEARS);
        System.out.println(until);
    }

    public static class C1<E> {
        public void m1() {
        }
    }

    public static class DoSay implements Say {

        @Override
        public void sayName(String name) {
            System.out.println(name);
        }

        private void giveMeSay(Say say) {
            say.sayName("叽里呱啦");
        }
    }


    @Test
    public void demo8() {
        DoSay doSay = new DoSay();
        doSay.giveMeSay(doSay);
    }

    public void m2(Integer id, Consumer<String> callback) {

    }

    public static class ConsumerTest {
        private Integer id;
        private Consumer<String> callback;

        public ConsumerTest(Integer id, Consumer<String> callback) {
            this.id = id;
            this.callback = callback;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Consumer<String> getCallback() {
            return callback;
        }

        public void setCallback(Consumer<String> callback) {
            this.callback = callback;
        }
    }

    public void cTSayName(String name) {
        System.out.println("my name is" + name);
    }

    @Test
    public void demo9() {
        ConsumerTest consumerTest = new ConsumerTest(1, this::cTSayName);
        consumerTest.callback.accept("CT1");
    }

    public static class Ball {
        public Integer size;

        public Ball(Integer size) {
            this.size = size;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "size=" + size +
                    '}';
        }
    }

    @FunctionalInterface
    public interface GetBall {
        Ball getBall(Integer size);
    }

    private GetBall getBall;

    public void printBall(GetBall getBall) {
        this.getBall = getBall;
    }

    @Test
    public void emo10() {
        printBall(Ball::new);
    }

    public void validInput(String name, Consumer<String> function) {
        function.accept(name);
    }

    @Test
    public void demo11() {
        validInput("hahaha", inputStr -> System.out.println(inputStr.isEmpty() ? "不能为空" : inputStr));
        validInput("", inputStr -> System.out.println(inputStr.isEmpty() ? "不能为空" : inputStr));
    }

    @Test
    public void demo12() {
        for (int i = 126; i <= 135; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void demo13() {
//
        System.out.println(106640 / 60);
    }

    public List<String> list;

    @Test
    public void demo14() {
        IntStream.rangeClosed(5, 3).forEach(System.out::println);
    }

    @Test
    public void demo15() {
        for (int i = 1; i < 3; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void demo16() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(7);

        for (Integer integer : list) {
            if (integer == 5) {
                integer = 6;
            }
        }
        System.out.println(list);
    }

    public static class C {
        Integer c;
    }

    @Test
    public void demo17() {
        System.out.println(new C().c);
    }

    @Test
    public void demo18() {
        System.out.println(Integer.MAX_VALUE);
    }

    public static class A {

    }

    public static class B {
        public static final A a = new A();
    }

    public static class D extends B {

    }

    @Test
    public void demo19() {
        B b = new B();
        System.out.println(b.a);
        D d = new D();
        System.out.println(d.a);
    }

    @Test
    public void demo20() {
        System.out.println((58 / 10) * 10);
    }

    @Test
    public void demo21() {
        for (int i = 0; i < 50; i++) {
            System.out.println(new Random().nextInt(5));
        }
    }

    @Test
    public void demo22() {
        int[] a = {2, 34, 654, 8, 968, 654, 64, 654, 64, 6, 4};
        List<Integer> list = Arrays.stream(a).boxed().collect(Collectors.toList());
        int[] b = {64, 8,654};
        List<Integer> list1 = Arrays.stream(b).boxed().collect(Collectors.toList());
        list.removeAll(list1);
        System.out.println(list);
    }

    @Test
    public void demo23() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Random().nextInt(1));
        }
    }

    @Test
    public void demo24() {
        int[] asda = {2, 34, 654, 8, 968, 654, 64, 654, 64, 6, 4};
        List<Integer> list = Arrays.stream(asda).boxed().collect(Collectors.toList());

        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void demo25() {
        for (int i = 0; i <= 80; i += 10) {
            System.out.println(i);
        }
    }

    @Test
    public void demo26() {
        ArrayList<Integer> pass = new ArrayList<>();
        HashMap<Integer, Integer> turntable = new HashMap<>();
        turntable.put(1, 218700);
        turntable.put(7051, 218700);
        turntable.put(7119, 72900);
        turntable.put(7052, 72900);
        turntable.put(7053, 24300);
        turntable.put(7106, 24300);
        turntable.put(7122, 8100);
        turntable.put(13009, 8100);
        turntable.put(7128, 2700);
        turntable.put(13022, 2700);
        turntable.put(7129, 900);
        turntable.put(20032, 900);
        turntable.put(7054, 300);
        turntable.put(34043, 300);
        turntable.put(7004, 100);
        turntable.put(20045, 100);
        while (turntable.size() > 0) {
            int sum = turntable.values().stream().mapToInt(e -> e).sum();
            int target = new Random().nextInt(sum);
            AtomicInteger flag = new AtomicInteger();
            AtomicBoolean endLoop = new AtomicBoolean(false);
            AtomicInteger key = new AtomicInteger();
            turntable.forEach((k, v) -> {
                if (endLoop.get()) return;
                flag.addAndGet(v);
                if (flag.intValue() > target) {
                    endLoop.set(true);
                    System.out.println("target:[" + target + "]\t\tflag:[" + flag + "]\t\tk:[" + k + "]\tv:[" + v + "]\tturntable.size:[" + turntable.size() + "]");
                    String s1 = "target:[" + target + "]";
                    String s2 = "flag:[" + flag + "]";
                    String s3 = "k:[" + k + "]";
                    try {
                        demo30(s1, s2, s3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    key.set(k);
                }
            });
            turntable.remove(key.get());
        }
    }

    @Test
    public void demo27() {
        AtomicBoolean endLoop = new AtomicBoolean(false);
        AtomicInteger sum = new AtomicInteger();
        IntStream.rangeClosed(0, 10).forEach(e -> {
            if (endLoop.get()) return;
            sum.addAndGet(e);
            System.out.println(sum);
            if (sum.intValue() >= 10) {
                endLoop.set(true);
            }
        });
    }

    @Test
    public void demo28() {
        System.out.println("target:[31]".length() / 4);
        System.out.println("target:[1135]".length() / 4);
        System.out.println("k:[1]".length() / 4);
        System.out.println("k:[7052]".length() / 4);
    }

    @Test
    public void demo29() {
        System.out.println("target:[31]" + "\t\t*");
        System.out.println("target:[1135]" + "\t*");
        System.out.println("k:[1]" + "\t\t\t*");
        System.out.println("k:[7052]" + "\t\t*");
    }

    public void demo30(String... strings) throws Exception {
        List<Integer> list = new ArrayList<>();
        for (String str : strings) {
            list.add(str.length() / 4);
        }
        int max = list.stream().mapToInt(e -> e).max().orElseThrow(Exception::new) + 1;
        for (String str : strings) {
            System.out.print(str);
            for (int i = 0; i < max - str.length() / 4; i++) {
                System.out.print("\t");
            }
//            System.out.println("*");
        }
    }

    @Test
    public void demo31() throws Exception {
        demo30("target:[31]", "target:[1135]", "k:[1]", "k:[7052]");
    }

    @Test
    public void demo32() {
        AtomicBoolean isDone = new AtomicBoolean(true);
        System.out.println(isDone);
        if (isDone.get()) {
            System.out.println("ok");
        }
    }

    @Test
    public void demo33() {
        IntStream.rangeClosed(1,1).forEach(System.out::println);
    }
}

































































































