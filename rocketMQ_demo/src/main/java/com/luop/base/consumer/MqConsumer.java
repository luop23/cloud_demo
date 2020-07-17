package com.luop.base.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @Author: luoping
 * @Date: 2020/6/4 20:37
 * @Description: 消费消息
 */
@Slf4j
public class MqConsumer {

    public static void main(String[] args) throws Exception {
        //创建消费者，指定消费组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_3");
        //指定NameServer地址
        consumer.setNamesrvAddr("192.168.5.3:9876");
        //订阅主题Topic和标签Tag
        consumer.subscribe("Topic_1", "Tag_1");
        consumer.setMessageModel(MessageModel.BROADCASTING);   //广播模式   每个消费者消费的消息一样
//        consumer.setMessageModel(MessageModel.CLUSTERING);   //负载均衡模式  默认方式  所有消费者共同消费消息
        //设置回调函数，处理消息
        //接搜消息内容
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                log.info("消费消息：{}", new String(msg.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;   //消费成功
        });
        consumer.start();    //启动消费者
    }
}
