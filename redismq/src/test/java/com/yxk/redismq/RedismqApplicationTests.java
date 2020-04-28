package com.yxk.redismq;

import com.yxk.redismq.service.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedismqApplicationTests {


    @Autowired
    Publisher publisher;
    @Test
    void contextLoads() {
    }

    @Test
    void testTopic(){
        publisher.publish("hello world");
    }

}
