package com.lc.test.account.model;

import java.io.Serializable;
import java.util.*;

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
    private Date createTime;

    /**
    * 
    * isNullAble:1
    */
    private Date updateTime;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
