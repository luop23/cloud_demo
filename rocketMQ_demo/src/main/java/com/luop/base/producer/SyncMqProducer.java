package com.luop.base.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Author: luoping
 * @Date: 2020/6/4 19:51
 * @Description:  同步消息  保证消息可靠性 重要的消息通知，短信通知
 */
@Slf4j
public class SyncMqProducer {
    public static void main(String[] args) throws Exception {
        //创建消息生产Producer,并指定生产组名
        DefaultMQProducer mqProducer = new DefaultMQProducer("group_1");
        //指定NameServer地址
        mqProducer.setNamesrvAddr("192.168.5.3:9876");
        //启动producer
        mqProducer.start();
//        for (int i = 0; i < 10; i++) {
            //创建消息对象，指定Topic、Tag、消息内容
            Message message = new Message("Topic_1", "Tag_1", ("Hello RocketMQ sync message-" /*+ i*/).getBytes());
            //发送同步消息
            SendResult result = mqProducer.send(message);
            log.info("Result:{}",result);
//        }
        //关闭生产者
        mqProducer.shutdown();
    }
}
