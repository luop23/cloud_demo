package com.luop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: luoping
 * @Date: 2020/6/1 09:37
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("t_stock")
public class TStock implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 产品id
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 总库存
     */
    @TableField("total")
    private Integer total;

    /**
     * 已用库存
     */
    @TableField("used")
    private Integer used;

    /**
     * 剩余库存
     */
    @TableField("residue")
    private Integer residue;

    private static final long serialVersionUID = 1L;
}