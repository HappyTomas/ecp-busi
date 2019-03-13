package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-17 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromPresentDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private Long promId;

    private String sendType;//积分赠送类型  0按固定值送 1按倍数送
    
    private BigDecimal points;// 赠送积分  固定值  或者倍数
    
    private BigDecimal sendAmount;// 赠送优惠券数量
    
    private String coupId;//赠送优惠券id

    private List<PromGiftDTO> promGiftDTOList;//赠品列表
    
    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public BigDecimal getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(BigDecimal sendAmount) {
        this.sendAmount = sendAmount;
    }

    public String getCoupId() {
        return coupId;
    }

    public void setCoupId(String coupId) {
        this.coupId = coupId;
    }

    public List<PromGiftDTO> getPromGiftDTOList() {
        return promGiftDTOList;
    }

    public void setPromGiftDTOList(List<PromGiftDTO> promGiftDTOList) {
        this.promGiftDTOList = promGiftDTOList;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }
}
