package com.luop.delayMsg;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Author: luoping
 * @Date: 2020/6/4 19:51
 * @Description:  延时消息
 */
@Slf4j
public class Producer {
    public static void main(String[] args) throws Exception {
        //创建消息生产Producer,并指定生产组名
        DefaultMQProducer mqProducer = new DefaultMQProducer("group_delay");
        //指定NameServer地址
        mqProducer.setNamesrvAddr("192.168.5.3:9876");
        //启动producer
        mqProducer.start();
        for (int i = 0; i < 10; i++) {
            //创建消息对象，指定Topic、Tag、消息内容
            Message message = new Message("Topic_delay", "Tag_delay", ("Hello RocketMQ delay message-" + i).getBytes());
            message.setDelayTimeLevel(3);   //设置延时等级   让消费者延时消费
            //发送消息
            SendResult result = mqProducer.send(message);
            log.info("Result:{}",result);
        }
        //关闭生产者
        mqProducer.shutdown();
    }
}
