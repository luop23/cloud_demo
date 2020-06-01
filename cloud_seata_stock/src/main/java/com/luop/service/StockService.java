package com.luop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luop.entity.TStock;
import com.luop.entity.dto.StockDTO;

/**
 * @Author: luoping
 * @Date: 2020/6/1 13:17
 * @Description:
 */
public interface StockService extends IService<TStock> {

    //扣减库存
    boolean decreaseStock(StockDTO stockDTO);
}
