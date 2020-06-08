package com.luop.listener;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author: luoping
 * @Date: 2020/6/8 19:24
 * @Description:
 */
public interface Mysource {

    @Output("output1")
    MessageChannel output1();
}
