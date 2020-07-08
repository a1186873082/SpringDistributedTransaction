package com.lc.test.order.controller;

import com.lc.test.common.controller.base.BaseController;
import com.lc.test.common.entity.BaseResp;
import com.lc.test.order.model.TOrder;
import com.lc.test.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/add_order", method = RequestMethod.POST)
    public BaseResp addOrder(@RequestBody TOrder tOrder){
        return respByResult(orderService.addOrder(tOrder));
    }
}
