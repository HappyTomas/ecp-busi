package com.zengshi.ecp.busi.seller.coup.coupinfo.vo;

import java.util.Date;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-23 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupUseLimitVO extends EcpBaseResponseVO{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;

    private Long coupId;

    private String useRuleKey;

    private String useRuleValue;

    private String status;

    private String remark;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCoupId() {
        return coupId;
    }

    public void setCoupId(Long coupId) {
        this.coupId = coupId;
    }

    public String getUseRuleKey() {
        return useRuleKey;
    }

    public void setUseRuleKey(String useRuleKey) {
        this.useRuleKey = useRuleKey;
    }

    public String getUseRuleValue() {
        return useRuleValue;
    }

    public void setUseRuleValue(String useRuleValue) {
        this.useRuleValue = useRuleValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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


}
