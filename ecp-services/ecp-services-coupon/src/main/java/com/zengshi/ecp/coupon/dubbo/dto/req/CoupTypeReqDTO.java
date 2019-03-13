package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupTypeReqDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String coupTypeName;

    private String useRuleCode;

    private String getRuleCode;

    private String typeLimit;

    private String status;

    private Integer sortNo;

    private String remark;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;
    //创建开始时间
    private Date createTimeStart;
    //创建截止时间
    private Date createTimeEnd;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoupTypeName() {
        return coupTypeName;
    }

    public void setCoupTypeName(String coupTypeName) {
        this.coupTypeName = coupTypeName;
    }

    public String getUseRuleCode() {
        return useRuleCode;
    }

    public void setUseRuleCode(String useRuleCode) {
        this.useRuleCode = useRuleCode;
    }

    public String getGetRuleCode() {
        return getRuleCode;
    }

    public void setGetRuleCode(String getRuleCode) {
        this.getRuleCode = getRuleCode;
    }

    public String getTypeLimit() {
        return typeLimit;
    }

    public void setTypeLimit(String typeLimit) {
        this.typeLimit = typeLimit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

}