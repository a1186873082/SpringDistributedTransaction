package com.lc.test.account.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

@LocalTCC
public interface AccountAction {
    /**
     * name: tcc的bean的名称，全局需要唯一
     * commitMethod: try成功后的二阶段提交方法
     * rollbackMethod: try失败后的二阶段回滚方法
     * @BusinessActionContextParameter: 可传值到二阶段的参数
     *
     * @param paymentAmount
     * @return
     */
    @TwoPhaseBusinessAction(name = "pay", commitMethod = "commit", rollbackMethod = "fallback")
    public boolean pay(@BusinessActionContextParameter(paramName = "paymentAmount") BigDecimal paymentAmount);

    public boolean commit(BusinessActionContext context);

    public boolean fallback(BusinessActionContext  context);
}
