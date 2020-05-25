package com.luop.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luop.entity.Payment;
import com.luop.mapper.PaymentMapper;
import com.luop.service.PaymentService;
/**
 * @Author: luoping
 * @Date: 2020/5/25 17:34
 * @Description: 
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService{
}
