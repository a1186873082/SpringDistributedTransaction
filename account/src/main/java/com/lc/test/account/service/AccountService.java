package com.lc.test.account.service;

import com.lc.test.account.model.Account;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

public interface AccountService {

    boolean pay(BigDecimal paymentAmount);
}
