package com.zengshi.ecp.im.dao.model;

import com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by zhangys on 16/10/4.
 */
public class ImCustInfo implements Serializable {

    private Long id;

    private String custCode;

    private Integer custType;

    private String custCardId;

    private Long custGrowValue;

    private String custLevelCode;

    private String goodsId;

    private String orderId;

    private Long shopId;

    private Long reqTime;

    private Long businessType;
    //商品业务类型时是商品ID值,订单业务类型时是订单ID值
    private String businessId;

    private String ofStaffCode;

    private String custLevel;

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

    public Long getReqTime() {
        return reqTime;
    }

    public void setReqTime(Long reqTime) {
        this.reqTime = reqTime;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hashCode(this);
//    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImCustInfo)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ImCustInfo other = (ImCustInfo) obj;
        return this.getOfStaffCode().equals(other.getOfStaffCode())
            && this.getShopId().equals(other.getShopId());
    }

    @Override
    public String toString() {
        String jsonStr = JSON.toJSONString(this);
        return jsonStr;
    }

    public ImCustInfo() {

    }

    public ImCustInfo(ImCustReqDTO dto) {
        this.setOfStaffCode(dto.getOfStaffCode());
        this.setReqTime(System.currentTimeMillis());
        this.setBusinessId(dto.getBusinessId());
        this.setBusinessType(dto.getBusinessType());
        this.setCustLevel(dto.getCustLevel());
        this.setShopId(dto.getShopId());
    }
}
