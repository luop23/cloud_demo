package com.luop.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.luop.entity.TAccount;
import com.luop.mapper.TAccountMapper;
import com.luop.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/9 10:28
 * @Description:
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private TAccountMapper accountMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TAccount account) {
        accountMapper.updateById(account);
    }

    @Override
    public TAccount getByUserId(Long userId) {
        return accountMapper.selectOne(Wrappers.<TAccount>query().eq("user_id",userId));
    }
}
