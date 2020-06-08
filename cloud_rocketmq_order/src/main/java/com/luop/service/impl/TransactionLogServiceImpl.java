package com.luop.service.impl;

import com.luop.entity.RocketmqTransactionLog;
import com.luop.mapper.RocketmqTransactionLogMapper;
import com.luop.service.TransactionLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:43
 * @Description:
 */
@Service
public class TransactionLogServiceImpl implements TransactionLogService {

    @Resource
    private RocketmqTransactionLogMapper logMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTransactionLog(RocketmqTransactionLog log) {
        logMapper.insert(log);
    }
}
