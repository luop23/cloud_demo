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
import lombok.experimental.Accessors;

/**
 * @Author: luoping
 * @Date: 2020/6/8 17:18
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_account")
@Builder
@Accessors(chain = true)
public class TAccount implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 总额度
     */
    @TableField(value = "total")
    private BigDecimal total;

    /**
     * 已用额度
     */
    @TableField(value = "used")
    private BigDecimal used;

    /**
     * 剩余可用额度
     */
    @TableField(value = "residue")
    private BigDecimal residue;

    private static final long serialVersionUID = 1L;
}