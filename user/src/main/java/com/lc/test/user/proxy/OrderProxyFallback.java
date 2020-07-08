package com.lc.test.user.proxy;

import com.lc.test.common.controller.BaseController;
import com.lc.test.common.entity.BaseResp;
import com.lc.test.common.vo.TTestVo;
import org.springframework.stereotype.Component;

@Component
public class OrderProxyFallback extends BaseController implements OrderProxy {

    @Override
    public BaseResp addOrder(TTestVo testVo) {
        System.out.println("失败，执行降级处理");
        return fallback();
    }
}
