package cn.kang.bean;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * 结束的时候执行
 */
@SuppressWarnings("RedundantThrows")
@Component
public class MyDisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("结束");

    }

}
