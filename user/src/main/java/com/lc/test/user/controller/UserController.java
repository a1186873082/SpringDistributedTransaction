package com.lc.test.user.controller;

import com.lc.test.common.controller.BaseController;
import com.lc.test.common.entity.BaseResp;
import com.lc.test.common.vo.TTestVo;
import com.lc.test.user.model.TTest;
import com.lc.test.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public BaseResp addUser(@RequestBody TTestVo test) {
        return respByResult(userService.add(test));
    }
}
