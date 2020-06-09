package com.luop.service;

import com.luop.entity.TAccount;

/**
 * @Author: luoping
 * @Date: 2020/6/9 10:25
 * @Description:
 */
public interface AccountService {

    //修改
    void update(TAccount account);

    //根据userId查询数据
    TAccount getByUserId(Long userId);
}
