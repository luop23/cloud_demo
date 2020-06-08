package com.luop.orderMsg;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author: luoping
 * @Date: 2020/6/5 10:50
 * @Description: 消费者顺序消费
 */
@Slf4j
public class Consumer {
    public static void main(String[] args) throws Exception {
        //创建消费者，指定消费组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_group");
        //指定NameServer地址
        consumer.setNamesrvAddr("192.168.5.3:9876");
        //订阅主题Topic和标签Tag
        consumer.subscribe("order_topic", "*");
        //注册消息监听器
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                log.info("消费消息：线程名称【{}】--队列【{}】---{}", Thread.currentThread().getName(), msg.getQueueId(), new String(msg.getBody()));
            }
            return ConsumeOrderlyStatus.SUCCESS;    //消费成功
        });
        consumer.start();   //启动消费者
    }
}
