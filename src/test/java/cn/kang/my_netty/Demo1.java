package cn.kang.my_netty;

import cn.kang.utils.IniReader;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;

@SuppressWarnings({"unused", "UnusedAssignment"})
public class Demo1 {
    private Jedis jedis;

    @Before
    public void init() {
        jedis = new Jedis("114.116.175.209", 6379);
    }

    @Test
    public void demo1() {
        System.out.println(1 / 100);
    }

    @Test
    public void demo2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(new Random().nextInt(21) + 10);
        }
    }

    @Test
    public void demo3() {
        for (int i = 0; i < 100000; i++) {
            int a = new Random().nextInt(100000);
            int b = new Random().nextInt(100000);
            System.out.println(i + "     " + a + "     " + b);
            if (a == b) {
                System.out.println("******************");
                return;
            }
        }
    }

    @Test
    public void demo4() {
        Internal1.say();
        Internal1.say();
    }

    @Test
    public void demo5() throws JSONException {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray(list);
        jsonObject.put("a", jsonArray);
        System.out.println(jsonObject);
    }


    @Test
    public void demo6() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        System.out.println(map.get(3));
    }

    @Test
    public void demo7() {
        long startTime = new Date().getTime();
        System.out.println(16.0 / 9.0);
        System.out.println(Math.sqrt(20));
        double a = 0;
        int count = 0;
        while (true) {
            count++;
            a += 0.0000001;
            double b = a * 1.77777777777;
            double c = Math.sqrt((a * a) + (b * b));
            if (c > 68.58) {
                System.out.println("a:" + a + "    b:" + b + "     c:" + c + "    count:" + count);
                break;
            }
        }
        long endTime = new Date().getTime();
        System.out.print("耗时:");
        System.out.println(endTime - startTime);
    }

    @Test
    public void demo8() {
        System.out.println(new Date().getTime());
        System.out.println(new Date(new Date().getTime() + 1800000));
    }

    @Test
    public void demo9() {
        Ball ball = new Ball();
        List<Integer> a = ball.getA();
        a.add(1);
    }

    @Test
    public void demo10() {
        int i = new Random().nextInt(100);
        System.out.println(i);
    }

    @Test
    public void demo11() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    @Test
    public void demo12() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list);
        list.set(0, 999);
        System.out.println(list);
    }

    @Test
    public void demo13() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list);
        list.set(3, 5);
        System.out.println(list);
    }

    @Test
    public void demo14() {
        A a = new A();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        a.setList(list);
        a.setAnInt();
        System.out.println(a.getList());
        System.out.println(a.getAnInt());
        a.add();
        System.out.println(a.getList());
        System.out.println(a.getAnInt());
    }

    @Test
    public void demo15() {
        System.out.println(4600 / 0.8);
    }

    @Test
    public void demo16() {
        Thread shutdownThread = new Thread(() -> System.out.println("shutdownThread..."));
        Thread thread1 = new Thread(() -> System.out.println("thread1 run .."));
        Thread thread2 = new Thread(() -> System.out.println("thread2 run .."));
        Runtime.getRuntime().addShutdownHook(shutdownThread);
        thread1.start();
        thread2.start();
    }

    @Test
    public void demo17() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date());
            }
        });
        thread.run();
    }


    @Test
    public void demo18() {
        /*System.out.println(1995%12);
        System.out.println(1994%12);*/
        for (int i = 1997; i > 1950; i--) {
            /*if (i % 12 == 3) {
                System.out.println("猪"+i);
            }*/
            switch (i % 12) {
                case 3:
                    System.out.println("猪" + i);
                    break;
                case 2:
                    System.out.println("狗" + i);
                    break;
            }
        }
    }

    @Test
    public void demo19() {
        String str = "iphone";
        System.out.println("before:" + str);
        add(str);
        System.out.println("after :" + str);
        System.out.println("***********************************");

        StringBuilder sb = new StringBuilder("iphone");
        System.out.println("before:" + sb);
        append(sb);
        System.out.println("after :" + sb);
        System.out.println("***********************************");

        int i = 0;
        System.out.println("before:" + i);
        add(i);
        System.out.println("after :" + i);
        System.out.println("***********************************");

    }

    @Test
    public void demoT() {
        for (int i = 0; i < 200; i++) {
            boolean b = true;
            System.out.println(b + " " + i);
        }
    }

    @Test
    public void t1() {
        System.out.println(true);
    }

    public void add(String s) {
        s = s + "4";
    }

    public void add(int i) {
        System.out.println("5");
        i = 5;
    }

    public void add() {

    }

    public void append(StringBuilder stringBuilder) {
        stringBuilder.append("4");
    }

    @Test
    public void demo20() {
        DemoServcie demoServcie = new DemoServcie();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(new Service[]{demoServcie}));
        serviceManager.startAsync();
        serviceManager.stopAsync();
    }


    @Test
    public void demo22() {
        System.out.println(Demo1.class.getSimpleName());
        System.out.println(Demo1.class.getName());
    }

    @Test
    public void demo23() {
//        List<Parent> list = new ArrayList<Son>();
        List<? extends Parent> list1 = new ArrayList<Son>();
    }

    @Test
    public void demo24() {
        int a = 1;
        int b = 2;

    }

    @Test
    public void demo25() {
        System.out.println(1 << 1);
    }

    @Test
    public void demo26() throws JSONException {
        JSONObject put = new JSONObject().put(String.valueOf(1), 2);
        System.out.println(put);
    }

    @Test
    public void demo27() throws ParseException {
        String s = new String("2019.6.25.0.0-2019.6.26.0.0");
        System.out.println(s);
        String[] split = s.split("-");
        for (String str : split) {
            System.out.println(str);
            ParsePosition pos = new ParsePosition(0);
            Date parse = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").parse(str, pos);
            System.out.println(parse);
        }
    }

    @Test
    public void demo28() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", 1);
        System.out.println(jsonObject);
        jsonObject.put("1", 3);
        System.out.println(jsonObject);
    }

    @Test
    public void demo29() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        System.out.println(sdf.format(new Date()));
        Date parse = sdf.parse(format);
        System.out.println(parse);
        long time = parse.getTime();
        System.out.println(time);
    }

    @Test
    public void demo30() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(7051, 3);
        map.put(1, 1);
        map.put(51, 5);
        map.put(6, 678);
        map.put(32, 656);
        map.put(64, 168);
        System.out.println(map);
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println(list);
    }

    @Test
    public void demo31() {
        Parent p1 = new Parent("你好");
        Parent p2 = new Parent("woyehao");
        System.out.println(p1 == p2);
        ArrayList<Parent> parents = new ArrayList<>();
        parents.add(p1);
        parents.add(p2);
        System.out.println(parents);
        parents.forEach(e -> {
            if ("你好".equals(e.getName())) {
                e.setName("我好");
            }
        });
        System.out.println(parents);
    }

    @Test
    public void demo32() {
        LocalDate today = LocalDate.now();
        System.out.println(today);
        String s = today.toString();
    }

    @Test
    public void demo33() {
        Jedis jedis = new Jedis("114.116.175.209", 6379);
        jedis.set("1", "2");
    }

    @Test
    public void demo34() {
        String s = jedis.get("3");
        System.out.println(s);
    }

    @Test
    public void demo35() {
        IniReader iniReader = new IniReader("D:\\workSpace\\pokemon\\svn_local_repositorywc\\NOXGameServer\\config\\config.ini");
        String redis = iniReader.getIniKey("[redis]");
        System.out.println(redis);
    }

    @Test
    public void demo36() {
        System.out.println(109 / 100);
    }

    @Test
    public void demo37() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 30; i < 50; i++) {
            map.put(i, new Random().nextInt(100));
        }
        Set<Integer> set = map.keySet();
        System.out.println(set);
        Integer min = Collections.min(set);
        System.out.println(min);
    }

    @Test
    public void demo38() {
        Parent parent = new Parent("233");
        System.out.println(parent);
        parent.setName("333");
    }

    @Test
    public void demo39() {
        int[] array = {1, 2, 3, 5, 8, 13, 21, 34};
        int a = 1;
        int b = 2;
        int temp;
        for (int i = 0; i < 100; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
    }

    @Test
    public void demo40() {
        int[] array = new int[20];
        for (int arr : array) {
            arr = new Random().nextInt(20);
        }
    }

    @Test
    public void demo41() {
        long time = 604800000 / (1000 * 60 * 60 * 24);
        System.out.println(time);
    }

    @Test
    public void demo42() {
        for (int i = 15; i <= 19; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void demo43() {
        new Dog().wangWang();
        Dog.wangWang();
    }

    @Test
    public void dmoe44() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 10110);
        map.put(2, 10201);
        map.put(3, 0);
        ArrayList<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.forEach(e -> {
            System.out.println(e.getValue());
        });
    }

    @Test
    public void demo45() {
        Parent parent = new Parent("爸爸");
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
        map.put(4, 0);
        parent.setWifes(map);
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(parent.getWifes().entrySet());
        list.stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(System.out::println);
    }

    @Test
    public void demo46() {
        Parent parent = new Parent("爸爸");
        //parent.setWifes(new HashMap<>());
        Set<Map.Entry<Integer, Integer>> 爸爸 = parent.getWifes().entrySet();
    }

    @Test
    public void demo47() {
        List<String> array = Arrays.asList("a", "b", "c", "d");
        array.forEach(this::function1);
    }

    @Test
    public void demo48() {
        int count = 0;
        Map<Integer, Integer> map = Material.getMap();
        map.values()
                .stream()
                .sorted((a, b) -> Integer.compare(b, a))
                .forEach(System.out::println);
    }

    @Test
    public void demo50() {
        int sum = sum(1, 2, 3, 4);
        System.out.println(sum);
    }

    public int sum(Integer... integers) {
        return Arrays.stream(integers).reduce(Integer::sum).orElse(0);
    }

    @Test
    public void demo49() {
        Map<Integer, Integer> map = Material.getMap();
    }

    @Test
    public void demo51() {
        function1("哈哈1", this::print);
    }

    public void print(String str) {
        System.out.println("print value:" + str);
    }

    public void function1(String str, Consumer<String> callBack) {
        System.out.println("function1 执行");
        System.out.println(str);
    }

    public static class Tickers {
        public static final List<String> symbols = Arrays.asList(
                "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
                "AMZN", "CRAY", "CSCO", "DELL", "GOOG", "INTC", "INTU",
                "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static class YahooFinance {
        public static BigDecimal getPrice(final String ticker) {
            try {
                final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
                final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                final String data = reader.lines().skip(1).findFirst().get();
                final String[] dataItems = data.split(",");
                return new BigDecimal(dataItems[dataItems.length - 1]);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    private void function1(String str) {
        System.out.println("print value:" + str);
    }

    private static class Material {
        public static Map<Integer, Integer> getMap() {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(1, 2);
            map.put(2, 4);
            map.put(3, 3);
            map.put(4, 0);
            return map;
        }
    }

    private static class Dog {
        static void wangWang() {
            System.out.println("旺旺");
        }
    }

    @Test
    public void demo52() throws CloneNotSupportedException {
    }

    @Test
    public void demo53() {
        IntStream.rangeClosed(1, 10).forEach(System.out::println);
    }

    @Test
    public void demo54() {
        Consumer<Integer> c = System.out::print;
        c.accept(5464);
    }

    @Test
    public void demo55() {
        double sum = 100 * 0.25 + 92 * 0.75;
        System.out.println(sum);
    }

    @Test
    public void demo56() {
        Function<Integer, Integer> f = s -> ++s;
        Integer apply = f.apply(1);
        System.out.println(apply);
    }

    @Test
    public void demo57() {
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    @Test
    public void demo58() {
        Stream.iterate(0, n -> ++n).limit(10).forEach(System.out::println);
    }

    @Test
    public void demo59() {
        Function<Integer, Integer> f = e -> ++e;
        Function<Integer, Integer> g = e -> e * 2;
        System.out.println(f.compose(g).apply(1));
    }

    @Test
    public void demo60() {
        Predicate<Integer> p = e -> e == 0;
        System.out.println("你好");
    }

    @Test
    public void demo61() {
        double a = 0.3;
        double b = 0.7;
        double c;
        Function<Double, Double> f = e -> e = valueOf(e).add(valueOf(0.05)).doubleValue();
        for (int i = 0; i < 10; i++) {
            c = valueOf(1).subtract(valueOf(f.apply(a))).doubleValue();
            System.out.print("b=" + b + "    c=" + c + "    b*c=");
            b = valueOf(b).multiply(valueOf(c)).doubleValue();
            System.out.println(valueOf(b).toPlainString());
            a = f.apply(a);
        }
    }

    @Test
    public void demo62() {
        for (int j = 0; j < 100; j++) {
            int count = 0;
            int a;
            int d = 0;
            while (true) {
                boolean flag = false;
                a = 30;
                for (int i = 0; i < 10; i++) {
                    d = new Random().nextInt(100);
                    if (d <= a) {
                        flag = true;
                        break;
                    } else {
                        a += 5;
                    }
                }
                count++;
                if (!flag) {
                    break;
                }
            }
            System.out.println(count);
            System.out.println("a=" + a + "\t" + "d=" + d);
        }
    }

    @Test
    public void demo63() {
        System.out.println(new Random().nextInt(100));
    }

    @Test
    public void demo64() {
        BigDecimal a = valueOf(0);
        for (int i = 0; i < 1500000; i++) {
            a = a.add(valueOf(0.000001));
            System.out.println(a.multiply(a) + "\t\t" + a);
        }
    }

    @Test
    public void demo65() {
        int[] array = {2, 6, 2, 7};
        int asInt = IntStream.of(array).max().getAsInt();
        System.out.println(asInt);
    }

    @Test
    public void demo66() {
        System.out.println(function2());
    }

    public int function2() {
        int i = 1;
        try {
            return i;
        } finally {
            i = 2;
        }
    }

    @Test
    public void demo67() {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1, 2);
        map.put(2, 2);
        ConcurrentHashMap<Integer, Integer> map1 = new ConcurrentHashMap<>();
        map1.put(1, 2);
        map1.put(2, 2);
        System.out.println(map1.equals(map));
    }

    public static class Car {
        Integer color = 1;
    }

    public static class Person implements Cloneable {

        private String name;

        public void say() {
            System.out.println("我叫" + name);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Person() {

        }

        public Person(String name) {
            this.name = name;
        }

        @Override
        public Person clone() throws CloneNotSupportedException {
            return (Person) super.clone();
        }
    }

    public static interface MyListener {
        void doSomething(String name);
    }

    public static class MyButton {
        private MyListener listener;

        public void setListener(MyListener listener) {
            this.listener = listener;
        }

        public void click(String name) {
            listener.doSomething(name);
        }
    }

    @Test
    public void demo68() {
        MyButton button = new MyButton();
        button.setListener(System.out::println);
    }


    public static abstract class Abstract<T> {
        public abstract T getSomething();
        // Uses inherited methods from Concrete class
    }

    public static class AbstractLambda<T> extends Abstract<T> {

        private final Supplier<? extends T> supplier;

        private AbstractLambda(Supplier<? extends T> supplier) {
            this.supplier = supplier;
        }

        @Override
        public T getSomething() {
            return this.supplier.get();
        }
    }

    @Test
    public void demo69() {
        AbstractLambda<String> a = new AbstractLambda<>(() -> "hello world");
        System.out.println(a.getSomething());
    }

    @FunctionalInterface
    public static interface Interface {
        void doSomething();

    }

    public static class Implements implements Interface {

        @Override
        public void doSomething() {

        }

        public Implements(Interface anInterface) {

        }
    }

    @Test
    public void demo70() {
        new Thread(() -> System.out.println("In Java8, There is Lambda expression!")).start();

        Interface anInterface = () -> {

        };

        Implements anImplements = new Implements(() -> {

        });
    }

    @Data
    public static class Cat {
        Integer name;

        Cat(Integer name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "name=" + name +
                    '}';
        }


    }

    @Test
    public void demo71() {
        Cat cat1 = new Cat(5);
        Cat cat2 = new Cat(4);
        Cat cat3 = new Cat(6);
        List<Cat> cats = new ArrayList<>();

        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);

        cats.sort(Comparator.comparingInt(a -> a.name));
        System.out.println(cats);
    }

    private void method1(String str, Consumer<Object> callback) {
        System.out.println(str);
        callback.accept("哈哈");
    }

    @Test
    public void demo72() {
        method1("呵呵", System.out::println);
    }

    @Test
    public void demo73() {
        Integer a = null;
        System.out.println(a);
    }

    @Test
    public void demo74() {
        ABB abb = new ABB(null);
    }

    @Test
    public void demo75() {
        System.out.println(100 >>> 1);
    }

    public static class ABB {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        ABB(String name) {
            this.name = name;
        }
    }

    //HH:mm:ss
    @Test
    public void demo76() throws ParseException {
/*        System.out.println(LocalDateTime.ofInstant(new Date(System.currentTimeMillis()).toInstant(), ZoneId.systemDefault()));
        long until = LocalDateTime.now().plusDays(1).until(LocalDateTime.now(), ChronoUnit.DAYS);
        System.out.println(until);*/
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
        System.out.println(localDateTime);
    }

    @Test
    public void demo77() {
        Cat cat1 = new Cat(666);
        Cat cat2 = new Cat(777);
        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        System.out.println(cats);
        cats.stream().filter(e -> e.getName() == 666).forEach(e -> cats.set(cats.indexOf(e), new Cat(555)));
        System.out.println(cats);
    }

    @Test
    public void demo78() throws Exception {
        List<Integer> integers = Arrays.asList(3, 7, 5, 9, 1);
        Integer integer = integers.stream().filter(e -> e > 4).sorted().findFirst().orElseThrow(Exception::new);
        System.out.println(integer);
    }

    @Test
    public void demo79() {
        LocalDateTime before = LocalDateTime.of(2019, 8, 15, 15, 59, 59, 999999999);
        LocalDateTime now = LocalDateTime.now();
        long until = before.until(now, ChronoUnit.DAYS);
        System.out.println(until);
    }

    @Test
    public void demo80() {
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        System.out.println(new Date(1565884800000L));
    }

    @Test
    public void demo81() {
        LocalDateTime localDateTime = DateTime2Millis.timestampToDatetime(new Date().getTime());
        System.out.println(localDateTime);
    }

    @Test
    public void demo82() {
        var cookieMap = new HashMap<String, String>();
        var str = "[__cfduid=dec43a22b15ec84c05bc7134fdc798f681569550122; expires=Sat, 26-Sep-20 02:08:42 GMT; path=/; domain=.zerobyw4.com; HttpOnly, kd5S_2132_saltkey=fsESsgG1; expires=Sun, 27-Oct-2019 02:08:42 GMT; Max-Age=2592000; path=/; HttpOnly, kd5S_2132_lastvisit=1569546522; expires=Sun, 27-Oct-2019 02:08:42 GMT; Max-Age=2592000; path=/, kd5S_2132_sid=kvzvnN; expires=Sat, 28-Sep-2019 02:08:42 GMT; Max-Age=86400; path=/, kd5S_2132_lastact=1569550122%09member.php%09logging; expires=Sat, 28-Sep-2019 02:08:42 GMT; Max-Age=86400; path=/, kd5S_2132_ulastactivity=bf69Kx4WBOET2RhHKSPIr9IoR14pZY5c6XCTNy%2BveE9LTHZe9U4l; expires=Sat, 26-Sep-2020 02:08:42 GMT; Max-Age=31536000; path=/, kd5S_2132_sid=kvzvnN; expires=Sat, 28-Sep-2019 02:08:42 GMT; Max-Age=86400; path=/, kd5S_2132_auth=63559kvcTQWL2yYYCtDy%2F8CMNg7zmxkTg1A6Pt8h8prmWI70GVGUE%2FX8PqwnHk6AlcHncMy%2F7SPwMpuMNpvANW4pqLct; path=/; HttpOnly, kd5S_2132_loginuser=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; Max-Age=0; path=/, kd5S_2132_activationauth=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; Max-Age=0; path=/, kd5S_2132_pmnum=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; Max-Age=0; path=/, kd5S_2132_lastcheckfeed=1732511%7C1569550122; expires=Sat, 26-Sep-2020 02:08:42 GMT; Max-Age=31536000; path=/, kd5S_2132_checkfollow=1; expires=Fri, 27-Sep-2019 02:09:12 GMT; Max-Age=30; path=/, kd5S_2132_lip=121.60.87.156%2C1569550113; path=/, kd5S_2132_auth=9d26FYVBMRFJ3Wk4fH%2BGAdBXhPzV7Fuxf%2BLogM03M8S1e4jm6f3onNIVZyZCGPdfWbCk%2Bm6S2eekH2rppBX515llxqZb; expires=Sat, 12-Oct-2019 02:08:42 GMT; Max-Age=1296000; path=/; HttpOnly, kd5S_2132_loginuser=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; Max-Age=0; path=/, kd5S_2132_activationauth=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; Max-Age=0; path=/, kd5S_2132_pmnum=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; Max-Age=0; path=/, kd5S_2132_tshuz_accountlogin=1732511; path=/]";
        var temp = str.replaceAll(",", ";");
        Arrays.stream(temp.split(";"))
                .map(String::trim)
                .filter(e -> e.startsWith("kd5S_2132_saltkey")
                        || e.startsWith("kd5S_2132_lip")
                        || e.startsWith("kd5S_2132_ulastactivity")
                        || e.startsWith("kd5S_2132_sid")
                        || e.startsWith("kd5S_2132_lastact")
                        || e.startsWith("kd5S_2132_lastcheckfeed")
                        || e.startsWith("kd5S_2132_auth")
                        || e.startsWith("kd5S_2132_tshuz_accountlogin")
                        || e.startsWith("kd5S_2132_lastvisit")
                )
                .map(e->e.split("="))
                .forEach(e->cookieMap.put(e[0],e[1]));
        var cookie = new StringBuilder();
        cookieMap.forEach((k,v)->{
            cookie.append(k).append(v).append(";");
        });
        System.out.println(cookie);
    }


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
        Thread.sleep(3000);
    }


}


class Parent {
    private String name = "爸爸";

    private HashMap<Integer, Integer> wifes = new HashMap<Integer, Integer>();

    HashMap<Integer, Integer> getWifes() {
        return wifes;
    }

    void setWifes(HashMap<Integer, Integer> wifes) {
        this.wifes = wifes;
    }

    public String getName() {
        return name;
    }

    Parent(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                '}';
    }
}

@SuppressWarnings("ALL")
class Son extends Parent {
    private String name = "儿子";

    public Son(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public String getSuperName() {
        return super.getName();
    }
}

class DemoServcie extends AbstractIdleService {

    @Override
    protected void startUp() {
        System.out.println("starUp方法执行");
    }

    @Override
    protected void shutDown() {
        System.out.println("shutDown方法执行");
    }
}

class Ball {
    private List<Integer> a;

    public List<Integer> getA() {
        return a;
    }

    public void setA(List<Integer> a) {
        this.a = a;
    }

    Ball() {

    }
}

class A {
    private ArrayList<Integer> list;

    private int anInt;

    int getAnInt() {
        return anInt;
    }

    void setAnInt() {
        this.anInt = 1;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public void add() {
        this.list.add(1);
        this.anInt++;
    }
}




























