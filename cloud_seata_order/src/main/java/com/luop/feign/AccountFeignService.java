package com.luop.feign;

import com.luop.entity.CommonResult;
import com.luop.entity.dto.AccountDTO;
import com.luop.feign.fallback.AccountFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: luoping
 * @Date: 2020/6/1 09:58
 * @Description:
 */
@FeignClient(value = "cloud-seata-account",fallback = AccountFallback.class)
public interface AccountFeignService {

    @PutMapping("/account/decreaseMoney")
    CommonResult decreaseMoney(@RequestBody AccountDTO accountDTO);
}
