package com.lc.test.order.service.impl;

import com.lc.test.order.mapper.TOrderMapper;
import com.lc.test.order.model.TOrder;
import com.lc.test.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TOrderMapper orderMapper;

    @Override
    @Transactional
    public int addOrder(TOrder tOrder) {
        return orderMapper.insertTOrder(tOrder);
    }
}
