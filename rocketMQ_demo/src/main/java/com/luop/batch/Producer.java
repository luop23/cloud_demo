package com.luop.batch;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: luoping
 * @Date: 2020/6/5 13:37
 * @Description: 批量发送消息
 */
@Slf4j
public class Producer {
    public static void main(String[] args) throws Exception {
        //创建消息生产Producer,并指定生产组名
        DefaultMQProducer mqProducer = new DefaultMQProducer("group_batch");
        //指定NameServer地址
        mqProducer.setNamesrvAddr("192.168.5.3:9876");
        //启动producer
        mqProducer.start();
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("topic_batch", "tag_A", "hello1 batch msg".getBytes()));
        messages.add(new Message("topic_batch", "tag_B", "hello2 batch msg".getBytes()));
        messages.add(new Message("topic_batch", "tag_C", "hello3 batch msg".getBytes()));
        SendResult result = mqProducer.send(messages);//批量发送消息   批量消息的默认大小最大为1M
        log.info("发送消息：{}",result);
        mqProducer.shutdown();   //关闭生产者
    }
}
