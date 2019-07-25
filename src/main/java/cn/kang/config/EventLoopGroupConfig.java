package cn.kang.config;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EventLoopGroupConfig {
    @Bean("bossGroup")
    public EventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }
    @Bean("workGroup")
    public EventLoopGroup workGroup() {
        return new NioEventLoopGroup();
    }
}
