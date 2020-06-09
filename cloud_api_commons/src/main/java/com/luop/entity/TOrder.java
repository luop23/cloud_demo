package com.luop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:17
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_order")
@Builder
public class TOrder implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 产品id
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 数量
     */
    @TableField(value = "count")
    private Integer count;

    /**
     * 金额
     */
    @TableField(value = "money")
    private BigDecimal money;

    /**
     * 订单状态(0：创建中；1：已完成)
     */
    @TableField(value = "status")
    private Integer status;

    private static final long serialVersionUID = 1L;

}