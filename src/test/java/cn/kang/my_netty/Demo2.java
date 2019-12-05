package cn.kang.my_netty;

import com.google.common.eventbus.EventBus;
import com.google.protobuf.InvalidProtocolBufferException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.codec.binary.Base64;
import org.assertj.core.util.Lists;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings("RedundantThrows")
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


    @SuppressWarnings("unused")
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
        System.out.println(B.a);
        D d = new D();
        System.out.println(B.a);
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
        int[] b = {64, 8, 654};
        List<Integer> list1 = Arrays.stream(b).boxed().collect(Collectors.toList());

        list1.forEach(e -> list.removeIf(f -> f.equals(e)));
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
        System.out.println("☮");
    }

    public static class E {
        String name = "";
    }

    public static class F {
        String name = "";

        public F(E e) {
            this.name = e.name;
        }

        public F(Class<? extends E> e) {
            e.getName();
        }

        public void sayName() {
            System.out.println(this.name);
        }
    }

    @Test
    public void demo34() {
        E e = new E();
        e.name = "哈哈";
        Class<? extends E> aClass = e.getClass();
        F f = new F(e.getClass());
    }

    @Test
    public void demo35() {
        var list = new ArrayList<Integer>();
        IntStream.rangeClosed(1, 10000).parallel().forEach(list::add);
        System.out.println(list.size());
    }

    @Test
    public void demo36() {
        var map = new HashMap<Integer, ArrayList<Integer>>();
        var list = new ArrayList<Integer>();
        map.put(1, Lists.newArrayList(1, 11, 111));
        map.put(2, Lists.newArrayList(2, 22, 222));
        map.put(3, Lists.newArrayList(3, 33, 333));
        List<ArrayList<Integer>> collect = map.keySet().stream().map(map::get).collect(Collectors.toList());
    }

    @Test
    public void demo37() throws IOException, InterruptedException {
        var mwx = "http://www.zerobyw4.com/plugin.php?id=jameson_manhua&a=read&zjid=45696";
        var request = HttpRequest.newBuilder()
                .uri(URI.create(mwx))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Cookie",
//                        "__cfduid=de439f576f99f024c6bae17800f5678291569378977; "
//                        + "_ga=GA1.2.379535895.1569378978; "
//                        + "_gid=GA1.2.1790094955.1569378978; "
//                        + "_gat_gtag_UA_120212798_1=1; "
//                        + "kd5S_2132_manhuakaiguan=1; "
//                        + "kd5S_2132_manhuamoshi=2; "
//                        + "kd5S_2132_manhua_pcadvcookie=5; "
//                        + "kd5S_2132_checkfollow=1; "
//                        + "kd5S_2132_sendmail=1; "
//                        + "kd5S_2132_noticeTitle=1; "
                        "kd5S_2132_saltkey=XCvJK1ZF;kd5S_2132_sid=Mu0NnU;kd5S_2132_tshuz_accountlogin=1732511;kd5S_2132_lastcheckfeed=1732511%7C1569555737;kd5S_2132_lastvisit=1569552137;kd5S_2132_ulastactivity=a9d1DvZriekOx%2BAxLdGx%2BsOgBGoJTBLpWdIt722eGZcGR4fo31FJ;kd5S_2132_lastact=1569555737%09member.php%09logging;kd5S_2132_lip=121.60.87.156%2C1569555518;kd5S_2132_auth=0191QgV8kQ26bQiCQAeAlLuxU0YdGEEs8FAZM71C8%2B4rW2u073iOYFpFi2qRoiiY74AcyqFSzfekCLhwL4gC0s2W39bJ;"
                )
                .GET()
                .build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    public static void downImages(String filePath, String imgUrl) {
        // 若指定文件夹没有，则先创建
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 截取图片文件名
        String fileName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1);

        // 文件名里面可能有中文或者空格，所以这里要进行处理。但空格又会被URLEncoder转义为加号
        String urlTail = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        // 因此要将加号转化为UTF-8格式的%20
        imgUrl = imgUrl.substring(0, imgUrl.lastIndexOf('/') + 1) + urlTail.replaceAll("\\+", "\\%20");

        // 写出的路径
        File file = new File(filePath + File.separator + fileName);

        try {
            // 获取图片URL
            URL url = new URL(imgUrl);
            // 获得连接
            URLConnection connection = url.openConnection();
            // 设置10秒的相应时间
            connection.setConnectTimeout(10 * 1000);
            // 获得输入流
            InputStream in = connection.getInputStream();
            // 获得输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            // 构建缓冲区
            byte[] buf = new byte[1024];
            int size;
            // 写入到文件
            while (-1 != (size = in.read(buf))) {
                out.write(buf, 0, size);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //爬虫下载漫画
    @Test
    public void demo38() throws IOException, InterruptedException {
        String url = "http://www.zerobyw4.com/plugin.php?id=jameson_manhua&a=read&zjid=45674";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        Document document = Jsoup.parse(httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body());
        Elements zjimg = document.getElementsByClass("zjimg text-center mt0 mb0");
        zjimg.forEach(e -> {
            String imgSrc = e.getElementsByTag("img").attr("src");
            System.out.println(imgSrc);
            downImages("d:/img1", imgSrc);
        });
    }

    @Test
    public void demo39() {
        var loginUrl = "http://www.zerobyw4.com/member.php?mod=logging&action=login&loginsubmit=yes&frommessage&loginhash=LPH5e&inajax=1";
        var formBody = "username=kangjiantsui&password=Iamwinner!";
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(URI.create(loginUrl))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formBody))
                .build();
        httpClient.sendAsync(loginRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::headers)
                .thenAccept(headers -> {
                    var temp = headers.map().get("set-cookie").toString().replaceAll(",", ";");
                    var cookieMap = new HashMap<String, String>();
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
                            .map(e -> e.split("="))
                            .forEach(e -> cookieMap.put(e[0], e[1]));
                    var cookie = new StringBuilder();
                    cookieMap.forEach((k, v) -> cookie.append(k).append("=").append(v).append(";"));
                    var allPageUrl = "http://www.zerobyw4.com/plugin.php?id=jameson_manhua&a=bofang&kuid=1657";
                    var allPageRequest = HttpRequest.newBuilder()
                            .uri(URI.create(allPageUrl))
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0")
                            .GET()
                            .build();
                    httpClient.sendAsync(allPageRequest, HttpResponse.BodyHandlers.ofString())
                            .thenApply(HttpResponse::body)
                            .thenAccept(body -> {
                                Document document = Jsoup.parse(body);
                                document.getElementsByClass("muludiv").parallelStream().forEach(e -> {
                                    var chapter = e.getElementsByClass("uk-button uk-button-default").text();
                                    var pageUrl = e.getElementsByClass("uk-button uk-button-default").attr("href").replaceAll("./", document.baseUri());
                                    var pageRequest = HttpRequest.newBuilder()
                                            .uri(URI.create(pageUrl))
                                            .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0")
                                            .header("Cookie", cookie.toString())
                                            .GET()
                                            .build();
                                    httpClient.sendAsync(pageRequest, HttpResponse.BodyHandlers.ofString())
                                            .thenApply(HttpResponse::body)
                                            .thenAccept(subBody -> {
                                                Document subDocument = Jsoup.parse(subBody);
                                                var zjimg = subDocument.getElementsByClass("zjimg text-center mt0 mb0").size() != 0
                                                        ? subDocument.getElementsByClass("zjimg text-center mt0 mb0")
                                                        : subDocument.getElementsByClass("uk-text-center mb0");
                                                zjimg.parallelStream().forEach(f -> {
                                                    String imgSrc = f.getElementsByTag("img").attr("src");
                                                    System.out.println(imgSrc);
//                                                    System.out.println("Thread"+Thread.currentThread().getId());
//                                                    downImages("d:/Onepunch Man/" + chapter, imgSrc);
                                                });
                                            }).join();
                                });
                            }).join();
                }).join();
    }

    @Test
    public void demo40() throws InvalidProtocolBufferException {
        byte[] bytes = DatatypeConverter.parseHexBinary("0000017108ad0210b9986ce212e6020a044153444b10954e1a076e6f783640766e220b312e302e302e39383836352a0c2e313232343031383232372e3299027b2269734a7362223a66616c73652c22757365724167656e74223a224d6f7a696c6c612f352e30202857696e646f7773204e542031302e303b2057696e36343b2078363429204170706c655765624b69742f3533372e333620284b48544d4c2c206c696b65204765636b6f29204368726f6d652f37372e302e333836352e3930205361666172692f3533372e3336222c2275646964223a223f3f3f3f3f3f3f3f3f3f222c226f73223a2257696e646f7773222c22706c6174666f726d223a3130312c2262726f7773657254797065223a226368726f6d65222c2262726f7773657256657273696f6e223a2237372e302e333836352e3930222c226f7356657273696f6e223a22222c226f734d61696e56657273696e223a307d3a0b312e302e302e393838363542004a0a313232343031383232375200");

    }

    @Test
    public void demo41() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(2, 3);
        map.forEach((k, v) -> {
            map.put(k, 1);
        });
        System.out.println(map);
    }

    @Test
    public void demo42() {
        var map = new HashMap<Integer, Integer>();
        map.put(1, 2);
        map.put(2, 3);
        boolean contains = map.containsKey(1);
        System.out.println(contains);
    }

    @Test
    public void demo43() {
        Function<String, String> f = str -> str + "哈哈";
        System.out.println(f.apply("111"));
    }

    @Test
    public void demo45() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("object", "object");
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        JSONObject jsonObject1 = new JSONObject(jsonArray.toString());
    }

    public enum IAPType {
        IAP_MONTH_CHARGE_SILVER(11),        //白银月卡
        IAP_MONTH_CHARGE_GOLDEN(12),        //黄金月卡
        IAP_MONTH_CHARGE_DIAMOND(13);

        IAPType(int type) {
            this.value = type;
        }

        public int value() {
            return this.value;
        }

        private int value;
    }

    @Test
    public void demo46() throws JSONException {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("1", "2");
        stringStringHashMap.put("2", "2");
        stringStringHashMap.put("3", "2");
        JSONObject jsonObject = new JSONObject(stringStringHashMap);
        System.out.println(jsonObject);
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            System.out.println(keys.next());
        }
    }

    @Test
    public void demo47() {
        System.out.println("\\");
        char c = "\\".toCharArray()[0];
        System.out.println(c);
    }

    @Test
    public void demo48() throws JSONException {
        TestClass1.say();
    }

    public static class TestClass1 {
        static void say() {
            ThreadLocal<String> tl = new ThreadLocal<>();
            String s = tl.get();
            System.out.println(s);
        }
    }

    @Test
    public void demo49() {
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("111");
        String s = tl.get();
        System.out.println(s);
        TestClass1.say();
    }

    @Test
    public void demo50() throws FileNotFoundException {
        BufferedReader in = new BufferedReader(new FileReader("aaa.txt"));
        in.lines().forEach(System.out::println);
    }

    private enum SocketState {
        NONE, OPENED, CLOSED;

        SocketState() {
        }
    }

    @Test
    public void demo51() throws InterruptedException {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new SimpleChannelInboundHandler() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("hello");
                            }
                        });
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .bind(8080)
                .sync()
                .channel()
                .closeFuture()
                .sync();
    }

    @Test
    public void demo52() throws InterruptedException {
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new SimpleChannelInboundHandler() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("hello i'm client");
                            }
                        });
                    }
                })
                .connect("localhost", 8080)
                .sync()
                .channel()
                .closeFuture()
                .sync();
    }

    @Test
    public void demo53() {
        System.out.println("\"cause\":\"java.lang.NullPointerExceptionntat com.leocool.sgland.service.ToolService$RequestHandler.doGetRolePayInfo(ToolService.java:136)ntat com.leocool.sgland.service.ToolService$RequestHandler.channelRead0(ToolService.java:89)ntat com.leocool.sgland.service.ToolService$RequestHandler.channelRead0(ToolService.java:53)ntat io.netty.channel.SimpleChannelInboundHandler.channelRead(SimpleChannelInboundHandler.java:105)ntat io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:308)ntat io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:294)ntat io.netty.handler.codec.MessageToMessageDecoder.channelRead(MessageToMessageDecoder.java:103)ntat io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:308)ntat io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:294)ntat io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:182)ntat io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:147)ntat io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:308)ntat io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:294)ntat com.leocool.channel.NOXIdleStateHandler.channelRead(NOXIdleStateHandler.java:152)ntat io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:308)ntat io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:294)ntat io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:846)ntat io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:130)ntat io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:511)ntat io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:468)ntat io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:382)ntat io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:354)ntat io.netty.util.concurrent.SingleThreadEventExecutor$2.run(SingleThreadEventExecutor.java:116)ntat io.netty.util.concurrent.DefaultThreadFactory$DefaultRunnableDecorator.run(DefaultThreadFactory.java:137)ntat java.lang.Thread.run(Thread.java:748)n\",\"error\":\"user not found0\"");
    }

    @Test
    public void demo54() {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Arrays.stream("java.lang.NullPointerExceptionntat com.leocool.sgland.service.ToolService$RequestHandler.doGetRolePayInfo(ToolService.java:136)ntat com.leocool.sgland.service.ToolService$RequestHandler.channelRead0(ToolService.java:89)ntat com.leocool.sgland.service.ToolService$RequestHandler.channelRead0(ToolService.java:53)ntat io.netty.channel.SimpleChannelInboundHandler.channelRead(SimpleChannelInboundHandler.java:105)ntat io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:308)ntat io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:294)ntat io.netty.handler.codec.MessageToMessageDecoder.channelRead(MessageToMessageDecoder.java:103)ntat io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:308)ntat io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:294)ntat io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:182)ntat io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:147)ntat io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:308)ntat io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:294)ntat com.leocool.channel.NOXIdleStateHandler.channelRead(NOXIdleStateHandler.java:152)ntat io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:308)ntat io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:294)ntat io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:846)ntat io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:130)ntat io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:511)ntat io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:468)ntat io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:382)ntat io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:354)ntat io.netty.util.concurrent.SingleThreadEventExecutor$2.run(SingleThreadEventExecutor.java:116)ntat io.netty.util.concurrent.DefaultThreadFactory$DefaultRunnableDecorator.run(DefaultThreadFactory.java:137)ntat java.lang.Thread.run(Thread.java:748)n".split("ntat")).forEach(System.out::println);
        }
    }

    @Test
    public void demo55() {
        jedis.set("key", "val", "NX", "EX", 20);

    }

    @Test
    public void demo56() throws InterruptedException {
        while (true) {
            System.out.println(jedis.get("key"));
            Thread.sleep(1000);
        }
    }

    public static final String QUEUE_NAME = "helloMQ";

    @Test
    public void demo57() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("114.116.175.209");
        try (Connection connection = factory.newConnection();
             com.rabbitmq.client.Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "hello world";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [X] Sent '" + message + "'");
        }
    }

    @Test
    public void demo58() {
        int[] a = {9, 3, 4, 8, 52, 1};
        for (int i = 0; i < a.length-1; i++) {
            if (a[i] < a[i + 1]) {
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }
        Stream.of(a).forEach(System.out::print);
        HashMap hashMap = new HashMap();

    }

}

































































































