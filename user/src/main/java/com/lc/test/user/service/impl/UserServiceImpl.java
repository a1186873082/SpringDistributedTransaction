package com.lc.test.user.service.impl;

import com.lc.test.user.mapper.TTestMapper;
import com.lc.test.user.model.TTest;
import com.lc.test.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TTestMapper testMapper;

    @Override
    public int add(TTest tTest) {
        return testMapper.insert(tTest);
    }
}
