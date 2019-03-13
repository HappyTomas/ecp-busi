package com.zengshi.ecp.busi.seller.coup.couptype.vo;

import java.util.Date;
import java.util.Map;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-19 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CouponTypeVO extends EcpBaseResponseVO{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String coupTypeName;

    private String useRuleCode;

    private String getRuleCode;

    private String typeLimit;
    
    private String typeLimitName;

    private String status;
    
    private String statusName;

    private Integer sortNo;

    private String remark;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;
    
    private Map useMap;//使用规则
    
    private Map receiveMap;//领取规则

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

    public String getTypeLimitName() {
        return typeLimitName;
    }

    public void setTypeLimitName(String typeLimitName) {
        this.typeLimitName = typeLimitName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public Map getUseMap() {
        return useMap;
    }

    public void setUseMap(Map useMap) {
        this.useMap = useMap;
    }

    public Map getReceiveMap() {
        return receiveMap;
    }

    public void setReceiveMap(Map receiveMap) {
        this.receiveMap = receiveMap;
    }

    private Long updateStaff;
    
    
 
}
