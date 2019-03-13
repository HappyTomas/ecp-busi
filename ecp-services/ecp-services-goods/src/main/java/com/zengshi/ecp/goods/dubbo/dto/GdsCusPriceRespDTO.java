package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceInfoResp;
public class GdsCusPriceRespDTO extends GdsPriceInfoResp {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
    private static final long serialVersionUID = -1L;
	
    private String priceTarget;

    private String status;

    private Long currencysId;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;




    public String getPriceTarget() {
        return priceTarget;
    }

    public void setPriceTarget(String priceTarget) {
        this.priceTarget = priceTarget;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCurrencysId() {
        return currencysId;
    }

    public void setCurrencysId(Long currencysId) {
        this.currencysId = currencysId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

}
