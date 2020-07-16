package com.lc.test.account.action.impl;

import com.lc.test.account.action.AccountAction;
import com.lc.test.account.mapper.AccountMapper;
import com.lc.test.account.model.Account;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class AccountActionImpl implements AccountAction {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public boolean pay(BigDecimal amount) {
        Account pay = accountMapper.selectOne(1);
        if (amount.compareTo(pay.getAmount()) > 0) {
            return false;
        }
        //支付完后的钱
        BigDecimal finishAmount = pay.getAmount().subtract(amount).setScale(2);
        BigDecimal freezeAmount = pay.getFreezeAmount().add(amount).setScale(2);
        //冻结金额
        pay.setAmount(finishAmount);
        pay.setFreezeAmount(freezeAmount);
        accountMapper.updateaccount(pay);

        return true;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        BigDecimal amount = (BigDecimal) context.getActionContext().get("paymentAmount");
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
    public boolean fallback(BusinessActionContext context) {
        BigDecimal amount = (BigDecimal) context.getActionContext().get("paymentAmount");
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
