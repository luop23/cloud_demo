package com.luop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luop.entity.TOrder;
import com.luop.entity.dto.AccountDTO;
import com.luop.entity.dto.StockDTO;
import com.luop.feign.AccountFeignService;
import com.luop.feign.StockFeignService;
import com.luop.mapper.TOrderMapper;
import com.luop.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: luoping
 * @Date: 2020/6/1 09:56
 * @Description:
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements OrderService {

    @Resource
    private TOrderMapper orderMapper;

    @Resource
    private AccountFeignService accountService;

    @Resource
    private StockFeignService stockService;

    @Override
    @GlobalTransactional(name = "tx_create_order", rollbackFor = Exception.class)
    public void createOrder(TOrder order) {
        log.info("--------->开始创建订单");
        orderMapper.insert(order);
        log.info("--------->调用库存微服务，扣减库存");
        stockService.decreaseStock(StockDTO.builder().productId(order.getProductId()).productCount(order.getCount()).build());
        log.info("--------->调用账户微服务，扣减金额");
        accountService.decreaseMoney(AccountDTO.builder().userId(order.getUserId()).costMoney(order.getMoney()).build());
        log.info("--------->修改订单状态");
        order.setStatus(1);   //将订单状态改为完成
        orderMapper.updateById(order);
        log.info("--------->订单创建成功，下单结束！");
    }

    @Override
    public void editOrder(TOrder order) {
        orderMapper.updateById(order);
    }
}
