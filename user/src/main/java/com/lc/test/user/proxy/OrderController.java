package com.lc.test.user.proxy;

import com.lc.test.common.controller.base.BaseController;
import com.lc.test.common.entity.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order")
public interface OrderController {

    @RequestMapping(value = "/add_order", method = RequestMethod.POST)
    public BaseResp addOrder();
}
