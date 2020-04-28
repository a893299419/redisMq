package com.yxk.redismq.config;

import com.yxk.redismq.core.RedisListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

@Configuration

public class redisConfig {
    private static final Log logger = LogFactory.getLog(redisConfig.class);

    @Bean
    public RedisListener redisListener(){
        return new RedisListener();
    }
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        logger.debug("redis序列化开始");
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //String序列化方式
        RedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        // 设置默认序列化方式
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        logger.debug("redis序列化配置结束");
        return template;
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener((MessageListener) redisListener(),new PatternTopic("demo-channel"));
        return container;
    }

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory factory){
        return new RedisLockRegistry(factory,"demo-lock",60);
    }
}
