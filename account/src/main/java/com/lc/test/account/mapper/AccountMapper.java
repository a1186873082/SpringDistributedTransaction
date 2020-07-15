package com.lc.test.account.mapper;

import java.util.List;

import com.lc.test.account.model.Account;
import org.apache.ibatis.annotations.Param;

/**
 * @author author
 */
public interface AccountMapper {

    int insertaccount(Account object);

    int updateaccount(Account object);

    Account selectOne(int id);
}