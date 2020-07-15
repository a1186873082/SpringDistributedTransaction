package com.lc.test.order.service.impl;

import com.lc.test.common.entity.BaseResp;
import com.lc.test.order.mapper.TOrderMapper;
import com.lc.test.order.model.TOrder;
import com.lc.test.order.proxy.AccountProxy;
import com.lc.test.order.service.OrderService;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TOrderMapper orderMapper;

    @Autowired
    private AccountProxy accountProxy;

    @Override
//    @Transactional
    @GlobalTransactional
    public int addOrder(TOrder tOrder) {
        return orderMapper.insertTOrder(tOrder);
    }

    @Override
    @GlobalTransactional
    public int payOrder(TOrder tOrder) {
        if (tOrder.getOrderPrice().compareTo(BigDecimal.ZERO) > 0 && tOrder.getOrderNum() > 0) {
            BigDecimal totalAmount = tOrder.getOrderPrice().multiply(new BigDecimal(tOrder.getOrderNum())).setScale(2);
            Double amount = totalAmount.doubleValue();
            BaseResp baseResp = accountProxy.pay(amount.toString());
            if (baseResp.getCode() == 1000) {
                //执行插入订单
                orderMapper.insertTOrder(tOrder);
            }
        }
        return -1;
    }
}
