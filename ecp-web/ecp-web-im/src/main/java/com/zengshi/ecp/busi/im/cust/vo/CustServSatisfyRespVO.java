package com.zengshi.ecp.busi.im.cust.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class CustServSatisfyRespVO extends EcpBaseResponseVO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long shopId;

    private String ofStaffCode;

    private String csaCode;

    private String sessionId;

    private String satisfyType;

    private String notSatisfyType;

    private String notSatisfyReason;
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOfStaffCode() {
        return ofStaffCode;
    }

    public void setOfStaffCode(String ofStaffCode) {
        this.ofStaffCode = ofStaffCode;
    }

    public String getCsaCode() {
        return csaCode;
    }

    public void setCsaCode(String csaCode) {
        this.csaCode = csaCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSatisfyType() {
        return satisfyType;
    }

    public void setSatisfyType(String satisfyType) {
        this.satisfyType = satisfyType;
    }

    public String getNotSatisfyType() {
        return notSatisfyType;
    }

    public void setNotSatisfyType(String notSatisfyType) {
        this.notSatisfyType = notSatisfyType;
    }

    public String getNotSatisfyReason() {
        return notSatisfyReason;
    }

    public void setNotSatisfyReason(String notSatisfyReason) {
        this.notSatisfyReason = notSatisfyReason;
    }

}
