package cn.kang.my_netty;

import cn.kang.my_netty.pokemon.PlayerWorld;
import com.google.protobuf.InvalidProtocolBufferException;
import com.leocool.sgland.protocol.Data;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {
    Connection conn = null;
    String sql;
    String conn_str;
    Statement stmt;

    @Before
    public void init() throws SQLException, ClassNotFoundException {
        conn_str = "jdbc:mysql://114.116.175.209/ygo_game_test?user=root&password=123&useUnicode=true&characterEncoding=UTF8";
        Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
        conn = DriverManager.getConnection(conn_str);
        stmt = conn.createStatement();
    }

    @Test
    public void test1() throws SQLException {
        try {
            sql = "show tables;";
            ResultSet result = stmt.executeQuery(sql);
            if (result != null) {
                while (result.next()) {
                    System.out.println(result.getString(1) + "\t");
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    @Test
    public void test2() throws Exception {
        sql = "select ID ,WorldMap from tb_user_map where ID=18";
        ResultSet result = stmt.executeQuery(sql);
        result.next();
        byte[] data = result.getBytes(2);
        Data.PlayerWorld playerWorld = Data.PlayerWorld.parseFrom(data);
        List<Integer> curLevels = new ArrayList<>(playerWorld.getCurLevelsList());
        System.out.println("pre:\t" + curLevels);
        System.out.println("after:\t" + curLevels);
        System.out.println(playerWorld);
        PlayerWorld pw = PlayerWorld.deserialize(data);
        Data.PlayerWorld pack = pw.pack();
        System.out.println(pack.equals(playerWorld));
        Data.PlayerWorld.Builder pb = playerWorld.toBuilder();
        for (int i = 0; i < curLevels.size(); i++) {
            if ((curLevels.get(i) - 10000) / 100 == 5) {
                pb.setCurLevels(i, 10511);
            }
        }
        Data.PlayerWorld build = pb.build();
        System.out.println(build.equals(playerWorld));
        byte[] bytes = build.toByteArray();
    }

    @Test
    public void test3() throws SQLException, InvalidProtocolBufferException {
        sql = "select ID ,WorldMap from tb_user_map where ID=18";
        ResultSet result = stmt.executeQuery(sql);
        result.next();
        byte[] data = result.getBytes(2);
        Data.PlayerWorld playerWorld = Data.PlayerWorld.parseFrom(data);
        System.out.println(playerWorld);
    }

    @Test
    public void test4() throws SQLException, InvalidProtocolBufferException {
        sql = "select Shop from tb_user_attach where ID = 24";
        ResultSet result = stmt.executeQuery(sql);
        result.next();
        byte[] data = result.getBytes(1);
        Data.PlayerShop shopData = Data.PlayerShop.parseFrom(data);

    }


}

















