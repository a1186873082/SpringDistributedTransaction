package com.lc.test.user.mapper;

import com.lc.test.user.model.TTest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TTestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_test
     *
     * @mbggenerated Wed Jul 08 16:35:31 CST 2020
     */
    int insert(TTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_test
     *
     * @mbggenerated Wed Jul 08 16:35:31 CST 2020
     */
    int insertSelective(TTest record);
}