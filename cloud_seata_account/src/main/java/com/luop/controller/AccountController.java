package com.luop.controller;

import com.luop.entity.CommonResult;
import com.luop.entity.TAccount;
import com.luop.entity.dto.AccountDTO;
import com.luop.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: luoping
 * @Date: 2020/6/1 14:19
 * @Description:
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @PutMapping("/decreaseMoney")
    public CommonResult decreaseMoney(@RequestBody AccountDTO accountDTO) {
        boolean result = accountService.decreaseMoney(accountDTO);
        if (result) {
            return new CommonResult(200, "扣减金额成功！");
        } else {
            return new CommonResult(444, "扣减金额失败，余额不足！");
        }
    }

    @GetMapping
    public CommonResult getAll() {
        List<TAccount> accounts = accountService.list();
        return new CommonResult(200, "查询成功！", accounts);
    }
}
