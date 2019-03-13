package com.zengshi.ecp.busi.prom.createprom.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-01-06 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author huangjx
 */
public class CoupInfoVO extends EcpBaseResponseVO{


    private static final long serialVersionUID = 1L;
   
    private Long id;//优惠券编码
    private String coupName;//优惠券名称
    private Long coupValue;//优惠券面额
    private String coupTypeName;//优惠券类型名称
    private Long coupTypeId;//优惠券类型id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCoupName() {
        return coupName;
    }
    public void setCoupName(String coupName) {
        this.coupName = coupName;
    }
    public Long getCoupValue() {
        return coupValue;
    }
    public void setCoupValue(Long coupValue) {
        this.coupValue = coupValue;
    }
    public String getCoupTypeName() {
        return coupTypeName;
    }
    public void setCoupTypeName(String coupTypeName) {
        this.coupTypeName = coupTypeName;
    }
    public Long getCoupTypeId() {
        return coupTypeId;
    }
    public void setCoupTypeId(Long coupTypeId) {
        this.coupTypeId = coupTypeId;
    }
}
