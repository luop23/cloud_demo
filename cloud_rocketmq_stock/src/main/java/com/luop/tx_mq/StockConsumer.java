package com.luop.tx_mq;

import com.luop.entity.TOrder;
import com.luop.entity.TStock;
import com.luop.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/9 10:13
 * @Description:
 */
@Component
@Slf4j
public class StockConsumer {

    @Resource
    private StockService stockService;

    //扣减库存
    @StreamListener(Sink.INPUT)
    public void decreaseStock(TOrder order) {
        log.info("扣减库存开始===================");
        TStock stock = stockService.getStockByProductId(order.getProductId());
        stock.setUsed(stock.getUsed() + order.getCount()).setResidue(stock.getResidue() - order.getCount());
        stockService.update(stock);
        log.info("扣减库存成功===================");
    }
}
