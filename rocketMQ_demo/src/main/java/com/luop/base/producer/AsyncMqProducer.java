package com.luop.base.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * @Author: luoping
 * @Date: 2020/6/4 20:39
 * @Description: 异步消息   保证响应时间短
 */
@Slf4j
public class AsyncMqProducer {

    public static void main(String[] args) throws Exception {
        //创建消息生产Producer,并指定生产组名
        DefaultMQProducer mqProducer = new DefaultMQProducer("group_1");
        //指定NameServer地址
        mqProducer.setNamesrvAddr("192.168.5.3:9876");
        //启动producer
        mqProducer.start();
        for (int i = 0; i < 10; i++) {
            //创建消息对象，指定Topic、Tag、消息内容
            Message message = new Message("Topic_2", "Tag_2", ("Hello RocketMQ async message-" + i).getBytes());
            //发送异步消息
            mqProducer.send(message, new SendCallback() {

                //发送成功回调函数
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("发送消息成功:{}",sendResult);
                }

                //发送失败回调函数
                @Override
                public void onException(Throwable e) {
                    log.error("发送消息异常:{}",e);
                }
            });
        }
        TimeUnit.SECONDS.sleep(10);   //保证生产者被关闭之前消息发送完
        //关闭生产者
        mqProducer.shutdown();
    }
}
