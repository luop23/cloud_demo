package com.luop.orderMsg;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @Author: luoping
 * @Date: 2020/6/5 10:24
 * @Description: 顺序生产消息
 */
@Slf4j
public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("order_group");   //构建生产者
        producer.setNamesrvAddr("192.168.5.3:9876");   //设置nameServer地址
        producer.start();  //启动生产者
        List<OrderStep> orderSteps = OrderStep.buildOrderSteps();   //构建需要发送的消息集合
        //发送消息
        for (int i = 0; i < orderSteps.size(); i++) {
            OrderStep orderStep = orderSteps.get(i);
            Message message = new Message("order_topic", "order_tag","i_"+i, orderStep.toString().getBytes());
            SendResult result = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    int id = (int) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderStep.getOrderId());
            log.info("发送结果：{}", result);
        }
        producer.shutdown();   //关闭生产者
    }
}
