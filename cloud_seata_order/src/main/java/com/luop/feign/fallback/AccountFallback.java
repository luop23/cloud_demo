package com.luop.feign.fallback;

import com.luop.entity.CommonResult;
import com.luop.entity.dto.AccountDTO;
import com.luop.feign.AccountFeignService;
import org.springframework.stereotype.Component;

/**
 * @Author: luoping
 * @Date: 2020/6/1 11:51
 * @Description:
 */
@Component
public class AccountFallback implements AccountFeignService {
    @Override
    public CommonResult decreaseMoney(AccountDTO accountDTO) {
        return new CommonResult(777, "服务宕机，请稍后再试！");
    }
}
