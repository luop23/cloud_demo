package com.luop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: luoping
 * @Date: 2020/6/1 09:34
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class TOrder implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 产品id
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 数量
     */
    @TableField("count")
    private Integer count;

    /**
     * 金额
     */
    @TableField("money")
    private BigDecimal money;

    /**
     * 订单状态(0：创建中；1：已完成)
     */
    @TableField("status")
    private Integer status;

    private static final long serialVersionUID = 1L;
}