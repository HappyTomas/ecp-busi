package com.zengshi.ecp.im.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

import java.sql.Timestamp;

/**
 * Created by zhangys on 16/10/4.
 */
public class ImCustReqDTO extends BaseInfo {

    private Long id;

    private String custCode;

    private Integer custType;

    private String custCardId;

    private Long custGrowValue;

    private String custLevelCode;

    private String goodsId;

    private String orderId;

    private Long shopId;

    private Long businessType;
    //商品业务类型时是商品ID值,订单业务类型时是订单ID值
    private String businessId;

    private Long reqTime;

    // 用户编号
    private String ofStaffCode;

    private String custLevel;
    
    // 原客服编号
    private String srcCsaCode;
    
    // 目标客服编号
    private String destCsaCode;

    public String getOfStaffCode() {
        return ofStaffCode;
    }

    public void setOfStaffCode(String ofStaffCode) {
        this.ofStaffCode = ofStaffCode;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public Long getReqTime() {
        return reqTime;
    }

    public void setReqTime(Long reqTime) {
        this.reqTime = reqTime;
    }

    public Long getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Long businessType) {
        this.businessType = businessType;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Integer getCustType() {
        return custType;
    }

    public void setCustType(Integer custType) {
        this.custType = custType;
    }

    public String getCustCardId() {
        return custCardId;
    }

    public void setCustCardId(String custCardId) {
        this.custCardId = custCardId;
    }

    public Long getCustGrowValue() {
        return custGrowValue;
    }

    public void setCustGrowValue(Long custGrowValue) {
        this.custGrowValue = custGrowValue;
    }

    public String getCustLevelCode() {
        return custLevelCode;
    }

    public void setCustLevelCode(String custLevelCode) {
        this.custLevelCode = custLevelCode;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

	public String getSrcCsaCode() {
		return srcCsaCode;
	}

	public void setSrcCsaCode(String srcCsaCode) {
		this.srcCsaCode = srcCsaCode;
	}

	public String getDestCsaCode() {
		return destCsaCode;
	}

	public void setDestCsaCode(String destCsaCode) {
		this.destCsaCode = destCsaCode;
	}

}
