package com.lc.test.order.proxy;

import com.lc.test.common.entity.BaseResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "account",fallback = AccountProxyFallback.class)
public interface AccountProxy {

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    BaseResp pay(@RequestBody String payAmount);
}
