package com.lc.test.user.service.impl;

import com.lc.test.common.entity.BaseResp;
import com.lc.test.common.vo.TTestVo;
import com.lc.test.user.mapper.TTestMapper;
import com.lc.test.user.model.TTest;
import com.lc.test.user.proxy.OrderProxy;
import com.lc.test.user.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TTestMapper testMapper;

    @Autowired
    private OrderProxy orderProxy;

    @Override
    @GlobalTransactional
    public int add(TTestVo tTestVo) {
        TTest tTest = new TTest();

        //向order模块发送添加order指令
        BaseResp orderResp = orderProxy.addOrder(tTestVo);

        BeanUtils.copyProperties(tTestVo, tTest);
        int result = testMapper.insertSelective(tTest);
        if(true){
            throw new RuntimeException();
        }
        return result;
    }
}
