package com.luop.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * @Author: luoping
 * @Date: 2020/6/4 20:54
 * @Description: 不关心发送结果的场景，比如发送日志
 */
public class OneWayMqProducer {

    public static void main(String[] args) throws Exception {
        //创建消息生产Producer,并指定生产组名
        DefaultMQProducer mqProducer = new DefaultMQProducer("group_1");
        //指定NameServer地址
        mqProducer.setNamesrvAddr("192.168.5.3:9876");
        //启动producer
        mqProducer.start();
        for (int i = 0; i < 10; i++) {
            //创建消息对象，指定Topic、Tag、消息内容
            Message message = new Message("Topic_3", "Tag_3", ("Hello RocketMQ oneWay message-" + i).getBytes());
            //发送单向消息
            mqProducer.sendOneway(message);
        }
        TimeUnit.SECONDS.sleep(10);   //保证发送一定量的消息
        //关闭生产者
        mqProducer.shutdown();
    }
}
