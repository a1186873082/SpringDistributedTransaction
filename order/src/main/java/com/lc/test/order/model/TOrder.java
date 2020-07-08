package com.lc.test.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author author
*/
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1594202906045L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private Integer orderId;

    /**
    * 
    * isNullAble:0,defaultVal:
    */
    private String orderCode;

    /**
    * 
    * isNullAble:0,defaultVal:0.00
    */
    private java.math.BigDecimal orderPrice;

    /**
    * 
    * isNullAble:0,defaultVal:0
    */
    private Integer orderNum;


    public void setOrderId(Integer orderId){this.orderId = orderId;}

    public Integer getOrderId(){return this.orderId;}

    public void setOrderCode(String orderCode){this.orderCode = orderCode;}

    public String getOrderCode(){return this.orderCode;}

    public void setOrderPrice(java.math.BigDecimal orderPrice){this.orderPrice = orderPrice;}

    public java.math.BigDecimal getOrderPrice(){return this.orderPrice;}

    public void setOrderNum(Integer orderNum){this.orderNum = orderNum;}

    public Integer getOrderNum(){return this.orderNum;}
}
