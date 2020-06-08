package com.luop.service;

import com.luop.entity.RocketmqTransactionLog;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:42
 * @Description:
 */
public interface TransactionLogService {

    //创建事务记录日志
    void createTransactionLog(RocketmqTransactionLog log);
}
