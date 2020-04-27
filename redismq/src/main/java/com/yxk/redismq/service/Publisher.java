package com.yxk.redismq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class Publisher {
    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redis;

    public void publish(Object msg){
        redis.convertAndSend("demo-channel",msg);
    }
}
