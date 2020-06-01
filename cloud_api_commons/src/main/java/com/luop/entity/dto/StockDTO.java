package com.luop.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: luoping
 * @Date: 2020/6/1 10:07
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDTO implements Serializable {
    private static final long serialVersionUID = 5934158314209808721L;

    //商品id
    private Long productId;

    //购买商品数量
    private Integer productCount;
}
