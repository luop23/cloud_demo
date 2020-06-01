package com.luop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luop.entity.TAccount;
import com.luop.entity.dto.AccountDTO;

/**
 * @Author: luoping
 * @Date: 2020/6/1 14:09
 * @Description:
 */
public interface AccountService extends IService<TAccount> {

    //扣减金额
    boolean decreaseMoney(AccountDTO accountDTO);
}
