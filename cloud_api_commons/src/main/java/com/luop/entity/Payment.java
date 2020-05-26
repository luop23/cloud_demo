package com.luop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: luoping
 * @Date: 2020/5/25 17:34
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    /**
     * id
     */
    private Long id;

    private String serial;

    private static final long serialVersionUID = 1L;
}