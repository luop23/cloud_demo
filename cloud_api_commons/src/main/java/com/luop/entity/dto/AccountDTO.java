package com.luop.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: luoping
 * @Date: 2020/6/1 10:19
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = -8040947672696641401L;

    //用户id
    private Long userId;

    //花费金额
    private BigDecimal costMoney;
}
