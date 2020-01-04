package cn.kang.my_netty;

import java.util.HashMap;
import java.util.Map;

public class ShoppingGiftTemp {
    private int shoppingId;
    private String activityUID;
    private int item1;
    private int count1;
    private int item2;
    private int count2;
    private int item3;
    private int count3;
    private int item4;
    private int count4;
    private int item5;
    private int count5;
    private int item6;
    private int count6;

    public int getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(int shoppingId) {
        this.shoppingId = shoppingId;
    }

    public String getActivityUID() {
        return activityUID;
    }

    public void setActivityUID(String activityUID) {
        this.activityUID = activityUID;
    }

    public int getItem1() {
        return item1;
    }

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public int getCount1() {
        return count1;
    }

    public void setCount1(int count1) {
        this.count1 = count1;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public int getCount2() {
        return count2;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public int getItem3() {
        return item3;
    }

    public void setItem3(int item3) {
        this.item3 = item3;
    }

    public int getCount3() {
        return count3;
    }

    public void setCount3(int count3) {
        this.count3 = count3;
    }

    public int getItem4() {
        return item4;
    }

    public void setItem4(int item4) {
        this.item4 = item4;
    }

    public int getCount4() {
        return count4;
    }

    public void setCount4(int count4) {
        this.count4 = count4;
    }

    public int getItem5() {
        return item5;
    }

    public void setItem5(int item5) {
        this.item5 = item5;
    }

    public int getCount5() {
        return count5;
    }

    public void setCount5(int count5) {
        this.count5 = count5;
    }

    public int getItem6() {
        return item6;
    }

    public void setItem6(int item6) {
        this.item6 = item6;
    }

    public int getCount6() {
        return count6;
    }

    public void setCount6(int count6) {
        this.count6 = count6;
    }

    @Override
    public String toString() {
        return "ShoppingGiftTemp{" +
                "shoppingId=" + shoppingId +
                ", activityUID='" + activityUID + '\'' +
                ", item1=" + item1 +
                ", count1=" + count1 +
                ", item2=" + item2 +
                ", count2=" + count2 +
                ", item3=" + item3 +
                ", count3=" + count3 +
                ", item4=" + item4 +
                ", count4=" + count4 +
                ", item5=" + item5 +
                ", count5=" + count5 +
                ", item6=" + item6 +
                ", count6=" + count6 +
                '}';
    }

    public Map<Integer, Integer> getItems() {
        Map<Integer, Integer> result = new HashMap<>();
        if (item1 != 0) {
            result.put(item1, count1);
        }
        if (item2 != 0) {
            result.put(item2, count2);
        }
        if (item3 != 0) {
            result.put(item3, count3);
        }
        if (item4 != 0) {
            result.put(item4, count4);
        }
        if (item5 != 0) {
            result.put(item5, count5);
        }
        if (item6 != 0) {
            result.put(item6, count6);
        }
        return result;
    }
}
