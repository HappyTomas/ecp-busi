package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PromUserLimitDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private Long staffId;

    private Long promId;

    private String limitType;

    private String limitTypeValue;
    
    private Long promCntLimit;

    private Long buyCnt;

    private Long remaindCnt;
    
    private String optValue;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public String getLimitTypeValue() {
        return limitTypeValue;
    }

    public void setLimitTypeValue(String limitTypeValue) {
        this.limitTypeValue = limitTypeValue;
    }

    public Long getPromCntLimit() {
        return promCntLimit;
    }

    public void setPromCntLimit(Long promCntLimit) {
        this.promCntLimit = promCntLimit;
    }

    public Long getBuyCnt() {
        return buyCnt;
    }

    public void setBuyCnt(Long buyCnt) {
        this.buyCnt = buyCnt;
    }

    public Long getRemaindCnt() {
        return remaindCnt;
    }

    public void setRemaindCnt(Long remaindCnt) {
        this.remaindCnt = remaindCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public String getOptValue() {
        return optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue;
    }

    private Date createTime;

    private Long createStaff;
 
}