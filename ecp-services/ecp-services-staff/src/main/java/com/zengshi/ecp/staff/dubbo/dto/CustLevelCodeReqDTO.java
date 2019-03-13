package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CustLevelCodeReqDTO extends BaseInfo {
    /** 
     * serialVersionUID:TODO(会员等级成长值明细与变更使用). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    public Long getGrowValue() {
        return growValue;
    }
    public void setGrowValue(Long growValue) {
        this.growValue = growValue;
    }
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public String getSourceType() {
        return sourceType;
    }
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
    public String getOrdId() {
        return ordId;
    }
    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }
    private Long growValue;
    private Long staffId;
    private String sourceType;
    private String ordId;
}