package com.luop.tx;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.TimeUnit;

/**
 * @Author: luoping
 * @Date: 2020/6/5 14:08
 * @Description: 事务消息
 */
@Slf4j
public class Producer {
    public static void main(String[] args) throws Exception {
        TransactionMQProducer producer = new TransactionMQProducer("tx_group");   //创建事务性消费者
        producer.setNamesrvAddr("192.168.5.3:9876");   //设置nameServer地址
        //创建事务监听器并设置给生产者
        producer.setTransactionListener(new TransactionListener() {
            //执行本地事务
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                log.info("============>{}", msg.getTags());
                if (StringUtils.equals(msg.getTags(), "TAGA")) {
                    return LocalTransactionState.COMMIT_MESSAGE;    //事务提交
                } else if (StringUtils.equals(msg.getTags(), "TAGB")) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;   //事务回滚
                }
                return LocalTransactionState.UNKNOW;    //由超时等其他原因造成的未响应状态
            }

            //进行状态回查
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                log.info("状态回查---标签为：{}", msg.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        producer.start();    //启动生产者
        String[] tags = {"TAGA", "TAGB", "TAGC"};
        for (int i = 0; i < 3; i++) {
            Message message = new Message("topic_tx", tags[i], ("发送事务消息-" + tags[i]).getBytes());
            TransactionSendResult result = producer.sendMessageInTransaction(message, null);
            log.info("结果是：{}", result);
        }
        TimeUnit.SECONDS.sleep(5);   //模拟业务逻辑
        producer.shutdown();   //关闭生产者
    }
}
