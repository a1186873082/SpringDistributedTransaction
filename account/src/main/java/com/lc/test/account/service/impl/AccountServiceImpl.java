package com.lc.test.account.service.impl;

import com.lc.test.account.mapper.AccountMapper;
import com.lc.test.account.model.Account;
import com.lc.test.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int pay(BigDecimal amount) {
        Account pay = accountMapper.selectOne(1);
        if (amount.compareTo(pay.getAmount()) > 0) {
            return -1;
        }
        //支付完后的钱
        BigDecimal finishAmount = pay.getAmount().subtract(amount).setScale(2);
        BigDecimal freezeAmount = pay.getFreezeAmount().add(amount).setScale(2);
        //冻结金额
        pay.setAmount(finishAmount);
        pay.setFreezeAmount(freezeAmount);
        accountMapper.updateaccount(pay);
        return 0;
    }

    @Override
    public boolean commit(BigDecimal amount) {
        Account pay = accountMapper.selectOne(1);
        Account receive = accountMapper.selectOne(2);
        BigDecimal freezeAmount = pay.getFreezeAmount().subtract(amount);
        pay.setFreezeAmount(freezeAmount);
        accountMapper.updateaccount(pay);

        BigDecimal receiveAmount = receive.getAmount().add(amount);
        receive.setAmount(receiveAmount);
        accountMapper.updateaccount(receive);
        return true;
    }

    @Override
    public boolean fallback(BigDecimal amount) {
        Account pay = accountMapper.selectOne(1);
        //支付完后的钱
        BigDecimal finishAmount = pay.getAmount().add(amount).setScale(2);
        BigDecimal freezeAmount = pay.getFreezeAmount().subtract(amount).setScale(2);
        //冻结金额
        pay.setAmount(finishAmount);
        pay.setFreezeAmount(freezeAmount);
        accountMapper.updateaccount(pay);
        return true;
    }
}
