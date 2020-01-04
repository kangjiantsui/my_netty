package cn.kang.my_netty;

import com.alibaba.druid.pool.DruidDataSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo3 {

    public static Connection conn = null;

    @Before
    public void before() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://114.116.175.209:3306/crawler");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
        conn = dataSource.getConnection();
    }

    @After
    public void after() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    public void demo1() {
        URI uri = URI.create("http://www.syyz.net/Item/5612.aspx");
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        httpClient.sendAsync(getRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(body -> {
                    Document document = Jsoup.parse(body);
                    document.getElementsByTag("tbody")
                            .forEach(e -> e.getElementsByTag("tr").forEach(f -> {
                                String name = f.getElementsByTag("td").get(0).text().replaceAll("\\s*", "");
                                String school = f.getElementsByTag("td").get(1).text().replaceAll("\\s*", "");
                                String speciality = f.getElementsByTag("td").get(2).text().replaceAll("\\s*", "");
                                String sql = "insert into tb_gaokao(name,school,speciality) values(?,?,?)";
                                try {
                                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                                    preparedStatement.setString(1, name);
                                    preparedStatement.setString(2, school);
                                    preparedStatement.setString(3, speciality);
                                    preparedStatement.execute();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }));
                })
                .join();
    }

    @Test
    public void demo2() {
    }
}
