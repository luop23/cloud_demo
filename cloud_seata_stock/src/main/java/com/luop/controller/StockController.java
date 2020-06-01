package com.luop.controller;

import com.luop.entity.CommonResult;
import com.luop.entity.dto.StockDTO;
import com.luop.service.StockService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/1 14:05
 * @Description:
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    private StockService stockService;

    @PutMapping("/decreaseStock")
    public CommonResult decreaseStock(@RequestBody StockDTO stockDTO) {
        boolean result = stockService.decreaseStock(stockDTO);
        if (result) {
            return new CommonResult(200, "扣减库存成功！");
        } else {
            return new CommonResult(444, "扣减库存失败，库存不足！");
        }
    }
}
