package com.lc.test.account.service.impl;

import com.lc.test.account.action.AccountAction;
import com.lc.test.account.mapper.AccountMapper;
import com.lc.test.account.model.Account;
import com.lc.test.account.service.AccountService;
import com.netflix.discovery.converters.Auto;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountAction accountAction;

    @Override
    @GlobalTransactional
    public boolean pay(BigDecimal paymentAmount) {
        boolean pay = accountAction.pay(paymentAmount);
        if (!pay) {
            throw new RuntimeException("TccActionOne failed.");
        }
        return true;
    }
}
