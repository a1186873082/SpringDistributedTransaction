package com.lc.test.order.proxy;

import com.lc.test.common.controller.BaseController;
import com.lc.test.common.entity.BaseResp;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AccountProxyFallback extends BaseController implements AccountProxy {

    @Override
    public BaseResp pay(Map<String, String> payAmountMap) {
        System.out.println("失败，执行降级处理");
        return fallback();
    }
}
