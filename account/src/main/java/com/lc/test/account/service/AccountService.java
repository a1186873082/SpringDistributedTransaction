package com.lc.test.account.service;

import com.lc.test.account.model.Account;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

public interface AccountService {

    @TwoPhaseBusinessAction(name = "pay", commitMethod = "commit", rollbackMethod = "fallback")
    int pay(BigDecimal amount);

    boolean commit(BigDecimal amount);

    boolean fallback(BigDecimal amount);
}
