package com.lc.test.common.controller.base;

import com.lc.test.common.entity.BaseResp;

public class BaseController {

    public BaseResp respByResult(int result) {
        BaseResp baseResp = new BaseResp();
        if (result > 0) {
            baseResp.setCode(1000);
            baseResp.setMessage("操作成功");
        } else {
            baseResp.setCode(1001);
            baseResp.setMessage("操作失败");
        }
        return baseResp;
    }
}
