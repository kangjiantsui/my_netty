package cn.kang.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogTest {
    public static final Logger logger = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.debug("hello");
    }
}
