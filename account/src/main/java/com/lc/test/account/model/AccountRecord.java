package com.lc.test.account.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author author
*/
public class AccountRecord implements Serializable {

    private static final long serialVersionUID = 1594808631726L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private Integer recordId;

    /**
    * 交易账户A
    * isNullAble:0
    */
    private Integer accountId;

    /**
    * 交易金额
    * isNullAble:0,defaultVal:0.00
    */
    private java.math.BigDecimal amount;

    /**
    * 交易状态
    * isNullAble:0,defaultVal:0
    */
    private Integer status;

    /**
    * 交易账户B
    * isNullAble:0
    */
    private Integer receiveId;

    /**
    * 
    * isNullAble:1
    */
    private java.time.LocalDateTime createTime;

    /**
    * 
    * isNullAble:1
    */
    private java.time.LocalDateTime updateTime;


    public void setRecordId(Integer recordId){this.recordId = recordId;}

    public Integer getRecordId(){return this.recordId;}

    public void setAccountId(Integer accountId){this.accountId = accountId;}

    public Integer getAccountId(){return this.accountId;}

    public void setAmount(java.math.BigDecimal amount){this.amount = amount;}

    public java.math.BigDecimal getAmount(){return this.amount;}

    public void setStatus(Integer status){this.status = status;}

    public Integer getStatus(){return this.status;}

    public void setReceiveId(Integer receiveId){this.receiveId = receiveId;}

    public Integer getReceiveId(){return this.receiveId;}

    public void setCreateTime(java.time.LocalDateTime createTime){this.createTime = createTime;}

    public java.time.LocalDateTime getCreateTime(){return this.createTime;}

    public void setUpdateTime(java.time.LocalDateTime updateTime){this.updateTime = updateTime;}

    public java.time.LocalDateTime getUpdateTime(){return this.updateTime;}
}
