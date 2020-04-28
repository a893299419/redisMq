package com.yxk.redismq.core;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;





public class RedisListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisListener.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        LOGGER.debug("从消息通道={}监听到消息",new String(pattern));
        LOGGER.debug("从消息通道={}监听到消息",new String(message.getChannel()));
        LOGGER.debug("元消息={}",new String(message.getBody()));
        // 新建一个用于反序列化的对象，注意这里的对象要和前面配置的一样
        // 因为我前面设置的默认序列化方式为GenericJackson2JsonRedisSerializer
        // 所以这里的实现方式为GenericJackson2JsonRedisSerializer
        RedisSerializer serializer=new GenericJackson2JsonRedisSerializer();
        LOGGER.debug("反序列化后的消息={}",serializer.deserialize(message.getBody()));
    }
}
