package cn.kang.my_netty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTime2Millis {
    public static LocalDateTime timestampToDatetime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
    public long datetimeToTimestamp(LocalDateTime ldt){
        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
