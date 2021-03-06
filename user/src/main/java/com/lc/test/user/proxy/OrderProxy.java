package com.lc.test.user.proxy;

import com.lc.test.common.entity.BaseResp;
import com.lc.test.common.vo.TTestVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "order", fallback = OrderProxyFallback.class)
public interface OrderProxy {

    @RequestMapping(value = "/order/add_order", method = RequestMethod.POST)
    BaseResp addOrder(@RequestBody TTestVo testVo);
}
