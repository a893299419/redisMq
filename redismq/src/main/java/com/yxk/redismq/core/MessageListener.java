package com.yxk.redismq.core;

import org.springframework.data.redis.connection.Message;
import reactor.util.annotation.Nullable;


public interface MessageListener {
    void onMessage(Message message, @Nullable byte[] pattern);
}
