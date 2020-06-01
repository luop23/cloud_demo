package com.luop.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luop.entity.TAccount;
import com.luop.entity.dto.AccountDTO;
import com.luop.mapper.TAccountMapper;
import com.luop.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: luoping
 * @Date: 2020/6/1 14:10
 * @Description:
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<TAccountMapper,TAccount> implements AccountService {

    @Resource
    private TAccountMapper accountMapper;

    @Override
    public boolean decreaseMoney(AccountDTO accountDTO) {
        TAccount account = accountMapper.selectOne(Wrappers.<TAccount>query().eq("user_id", accountDTO.getUserId()));
        if (account.getResidue().compareTo(accountDTO.getCostMoney()) < 0) {
            log.error("余额不足");
            return false;
        }
        int i = 1/0;   //模拟异常
        account.setUsed(account.getUsed().add(accountDTO.getCostMoney())).setResidue(account.getResidue().subtract(accountDTO.getCostMoney()));
        accountMapper.updateById(account);
        return true;
    }
}
