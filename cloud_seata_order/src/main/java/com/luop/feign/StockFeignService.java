package com.luop.feign;

import com.luop.entity.CommonResult;
import com.luop.entity.dto.StockDTO;
import com.luop.feign.fallback.StockFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: luoping
 * @Date: 2020/6/1 09:58
 * @Description:
 */
@FeignClient(value = "cloud-seata-stock",fallback = StockFallback.class)
public interface StockFeignService {

    //扣减库存
    @PutMapping("/stock/decreaseStock")
    CommonResult decreaseStock(@RequestBody StockDTO stockDTO);
}
