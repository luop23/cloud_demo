package com.luop.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.luop.entity.TStock;
import com.luop.mapper.TStockMapper;
import com.luop.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/9 10:10
 * @Description:
 */
@Service
public class StockServiceImpl implements StockService {

    @Resource
    private TStockMapper stockMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TStock stock) {
        stockMapper.updateById(stock);
    }

    @Override
    public TStock getStockByProductId(Long productId) {
        return stockMapper.selectOne(Wrappers.<TStock>query().eq("product_id",productId));
    }
}
