package com.luop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName(value = "rocketmq_transaction_log")
@Builder
public class RocketmqTransactionLog implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 事务id
     */
    @TableField(value = "transaction_id")
    private String transactionId;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    private static final long serialVersionUID = 1L;
}