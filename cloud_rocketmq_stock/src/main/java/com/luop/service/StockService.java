package com.luop.service;

import com.luop.entity.TStock;

/**
 * @Author: luoping
 * @Date: 2020/6/9 10:09
 * @Description:
 */
public interface StockService {

    //修改
    void update(TStock stock);

    //根据id查询数据
    TStock getStockByProductId(Long productId);
}
