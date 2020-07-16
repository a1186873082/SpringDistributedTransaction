package com.lc.test.account.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
*
*  @author author
*/
public class Account implements Serializable {

    private static final long serialVersionUID = 1594808622641L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private Integer accountId;

    /**
    * 账户名
    * isNullAble:0,defaultVal:
    */
    private String accountName;

    /**
    * 金额
    * isNullAble:0,defaultVal:0.00
    */
    private java.math.BigDecimal amount;

    /**
    * 冻结金额
    * isNullAble:0,defaultVal:0.00
    */
    private java.math.BigDecimal freezeAmount;

    /**
    * 状态
    * isNullAble:0,defaultVal:0
    */
    private Integer status;

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

    private BigDecimal payAmount;

    public void setAccountId(Integer accountId){this.accountId = accountId;}

    public Integer getAccountId(){return this.accountId;}

    public void setAccountName(String accountName){this.accountName = accountName;}

    public String getAccountName(){return this.accountName;}

    public void setAmount(java.math.BigDecimal amount){this.amount = amount;}

    public java.math.BigDecimal getAmount(){return this.amount;}

    public void setFreezeAmount(java.math.BigDecimal freezeAmount){this.freezeAmount = freezeAmount;}

    public java.math.BigDecimal getFreezeAmount(){return this.freezeAmount;}

    public void setStatus(Integer status){this.status = status;}

    public Integer getStatus(){return this.status;}

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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
