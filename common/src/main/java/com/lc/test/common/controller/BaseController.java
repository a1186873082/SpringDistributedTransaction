package com.lc.test.common.controller;

import com.lc.test.common.entity.BaseResp;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class BaseController {

    public BaseResp respByResult(Object result) {
        BaseResp baseResp = new BaseResp();
        if (result instanceof Integer) {
            int resultInt = (int) result;
            if (resultInt > 0) {
                baseResp.setCode(1000);
                baseResp.setMessage("操作成功");
            } else {
                baseResp.setCode(1001);
                baseResp.setMessage("操作失败");
            }
        } else if (result instanceof Boolean){
            boolean resultBool = (boolean) result;
            if (resultBool) {
                baseResp.setCode(1000);
                baseResp.setMessage("操作成功");
            } else {
                baseResp.setCode(1001);
                baseResp.setMessage("操作失败");
            }
        }

            return baseResp;
    }

    public BaseResp fallback() {
        BaseResp baseResp = new BaseResp();
        baseResp.setCode(1000);
        baseResp.setMessage("降级处理");
        return baseResp;
    }
}
