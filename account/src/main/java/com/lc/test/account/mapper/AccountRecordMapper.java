package com.lc.test.account.mapper;

import java.util.List;

import com.lc.test.account.model.AccountRecord;
import org.apache.ibatis.annotations.Param;

/**
 * @author author
 */
public interface AccountRecordMapper {

    int insertAccountRecord(AccountRecord object);

    int updateAccountRecord(AccountRecord object);
}