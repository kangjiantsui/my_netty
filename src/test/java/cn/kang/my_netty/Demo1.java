package cn.kang.my_netty;

import cn.kang.utils.IniReader;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import io.netty.channel.EventLoopGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }
    }

    @Test
    public void demo12() {
        List<Integer> list = new ArrayList();
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
        a.setAnInt(1);
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
        Thread shutdownThread = new Thread() {
            @Override
            public void run() {
                System.out.println("shutdownThread...");
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread1 run ..");
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread2 run ..");
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownThread);
        thread1.start();
        thread2.start();
    }

    @Test
    public void demo17() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date());
                }
            }
        };
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

    public void add(String s) {
        s = s + "4";
    }

    public void add(int i) {
        i = 5;
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
        System.out.println(new Demo1().getClass().getSimpleName());
        System.out.println(new Demo1().getClass().getName());
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
        ;
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
        int temp = 0;
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

    private static class Dog{
        public static void wangWang() {
            System.out.println("旺旺");
        }
    }
}


class Parent {
    private String name = "爸爸";

    public String getName() {
        return name;
    }

    public Parent(String name) {
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
    protected void startUp() throws Exception {
        System.out.println("starUp方法执行");
    }

    @Override
    protected void shutDown() throws Exception {
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

    public Ball() {

    }
}

class A {
    private ArrayList list;

    private int anInt;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public void add(int... ints) {
        this.list.add(1);
        this.anInt++;
    }
}




























