package com.zengshi.ecp.im.dao.model;

import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyReqDTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class ImCustservSatisfyInfo implements Serializable {
    private Long id;

    private Long shopId;

    private String ofStaffCode;

    private String csaCode;

    private String sessionId;

    private String satisfyType;

    private String notSatisfyType;

    private String notSatisfyReason;

    private Timestamp createDate;

    private static final long serialVersionUID = 1L;

    public ImCustservSatisfyInfo() {

    }
    public ImCustservSatisfyInfo(CustServSatisfyReqDTO reqDTO) {
        this.setShopId(reqDTO.getShopId());
        this.setCsaCode(reqDTO.getCsaCode());
        this.setOfStaffCode(reqDTO.getOfStaffCode());
        this.setSessionId(reqDTO.getSessionId());
        this.setSatisfyType(reqDTO.getSatisfyType());
        this.setNotSatisfyType(reqDTO.getNotSatisfyType());
        this.setNotSatisfyReason(reqDTO.getNotSatisfyReason());
        this.setCreateDate(new Timestamp(System.currentTimeMillis()));
    }

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
        this.ofStaffCode = ofStaffCode == null ? null : ofStaffCode.trim();
    }

    public String getCsaCode() {
        return csaCode;
    }

    public void setCsaCode(String csaCode) {
        this.csaCode = csaCode == null ? null : csaCode.trim();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public String getSatisfyType() {
        return satisfyType;
    }

    public void setSatisfyType(String satisfyType) {
        this.satisfyType = satisfyType == null ? null : satisfyType.trim();
    }

    public String getNotSatisfyType() {
        return notSatisfyType;
    }

    public void setNotSatisfyType(String notSatisfyType) {
        this.notSatisfyType = notSatisfyType == null ? null : notSatisfyType.trim();
    }

    public String getNotSatisfyReason() {
        return notSatisfyReason;
    }

    public void setNotSatisfyReason(String notSatisfyReason) {
        this.notSatisfyReason = notSatisfyReason == null ? null : notSatisfyReason.trim();
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", ofStaffCode=").append(ofStaffCode);
        sb.append(", csaCode=").append(csaCode);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", satisfyType=").append(satisfyType);
        sb.append(", notSatisfyType=").append(notSatisfyType);
        sb.append(", notSatisfyReason=").append(notSatisfyReason);
        sb.append(", createDate=").append(createDate);
        sb.append("]");
        return sb.toString();
    }
}