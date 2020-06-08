package com.luop.orderMsg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: luoping
 * @Date: 2020/6/5 10:18
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStep {

    private Integer orderId;

    private String description;

    public static List<OrderStep> buildOrderSteps() {
        List<OrderStep> orderSteps = new ArrayList<>();
        orderSteps.add(OrderStep.builder().orderId(1).description("创建").build());
        orderSteps.add(OrderStep.builder().orderId(1).description("付款").build());
        orderSteps.add(OrderStep.builder().orderId(2).description("创建").build());
        orderSteps.add(OrderStep.builder().orderId(2).description("付款").build());
        orderSteps.add(OrderStep.builder().orderId(2).description("推送").build());
        orderSteps.add(OrderStep.builder().orderId(3).description("创建").build());
        orderSteps.add(OrderStep.builder().orderId(3).description("付款").build());
        orderSteps.add(OrderStep.builder().orderId(3).description("推送").build());
        orderSteps.add(OrderStep.builder().orderId(3).description("完成").build());
        return orderSteps;
    }
}
