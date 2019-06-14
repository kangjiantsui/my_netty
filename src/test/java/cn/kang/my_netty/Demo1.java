package cn.kang.my_netty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.*;

public class Demo1 {
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
        int count=0;
        while (true) {
            count++;
            a += 0.0000001;
            double b = a * 1.77777777777;
            double c = Math.sqrt((a * a) + (b * b));
            if (c > 68.58) {
                System.out.println("a:" + a + "    b:" + b + "     c:" +c+"    count:"+count);
                break;
            }
        }
        long endTime = new Date().getTime();
        System.out.print("耗时:");
        System.out.println(endTime-startTime);
    }

    @Test
    public void demo8() {
        System.out.println(new Date().getTime());
        System.out.println( new Date(new Date().getTime()+1800000));
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

}

class Ball{
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

    public void add() {
        this.list.add(1);
        this.anInt++;
    }
}




























