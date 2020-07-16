package com.lc.test.account.controller;

import com.lc.test.account.model.Account;
import com.lc.test.account.service.AccountService;
import com.lc.test.common.controller.BaseController;
import com.lc.test.common.entity.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    /**
     * 此时没有加入用户，默认是1-》2，只用TCC解决分布式事务
     * @param account
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public BaseResp pay(@RequestBody Account account) {

        boolean result = accountService.pay(account.getPayAmount());
        return respByResult(result);
    }
}
