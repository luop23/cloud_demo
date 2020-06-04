package com.luop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: luoping
 * @Date: 2020/5/25 17:43
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResult {
    private Integer code;

    private String message;

    private Object data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
