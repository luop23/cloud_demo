package com.luop.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: luoping
 * @Date: 2020/6/8 15:49
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = -4294086560697052743L;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;
}
