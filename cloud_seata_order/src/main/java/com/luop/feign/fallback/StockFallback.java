package com.luop.feign.fallback;

import com.luop.entity.CommonResult;
import com.luop.entity.dto.StockDTO;
import com.luop.feign.StockFeignService;
import org.springframework.stereotype.Component;

/**
 * @Author: luoping
 * @Date: 2020/6/1 11:50
 * @Description:
 */
@Component
public class StockFallback implements StockFeignService {
    @Override
    public CommonResult decreaseStock(StockDTO stockDTO) {
        return new CommonResult(777, "服务宕机，请稍后再试！");
    }
}
