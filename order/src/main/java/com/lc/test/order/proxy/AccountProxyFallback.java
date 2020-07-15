package com.lc.test.order.proxy;

import com.lc.test.common.controller.BaseController;
import com.lc.test.common.entity.BaseResp;
import org.springframework.stereotype.Component;

@Component
public class AccountProxyFallback extends BaseController implements AccountProxy {

    @Override
    public BaseResp pay(String payAmount) {
        System.out.println("失败，执行降级处理");
        return fallback();
    }
}
