package com.luop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luop.entity.TStock;
import com.luop.entity.dto.StockDTO;
import com.luop.mapper.TStockMapper;
import com.luop.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/1 13:19
 * @Description:
 */
@Service
@Slf4j
public class StockServiceImpl extends ServiceImpl<TStockMapper, TStock> implements StockService {

    @Resource
    private TStockMapper stockMapper;

    @Override
    public boolean decreaseStock(StockDTO stockDTO) {
        QueryWrapper<TStock> queryWrapper = Wrappers.<TStock>query().eq("product_id", stockDTO.getProductId());
        TStock stock = stockMapper.selectOne(queryWrapper);
        if (stock.getResidue() < stockDTO.getProductCount()) {   //剩余库存与扣减库存比较
            log.error("库存不足");
            return false;
        }
        stock.setUsed(stock.getUsed() + stockDTO.getProductCount()).setResidue(stock.getResidue() - stockDTO.getProductCount());
        stockMapper.updateById(stock);
        return true;
    }
}
