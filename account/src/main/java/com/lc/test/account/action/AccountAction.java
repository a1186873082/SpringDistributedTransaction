package com.lc.test.account.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

@LocalTCC
public interface AccountAction {
    @TwoPhaseBusinessAction(name = "pay", commitMethod = "commit", rollbackMethod = "fallback")
    public boolean pay(BigDecimal amount, @BusinessActionContextParameter(paramName = "a") int a);

    public boolean commit(BusinessActionContext context);

    public boolean fallback(BusinessActionContext  context);
}
