package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;
import java.util.List;
import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CartPromDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
 
    private BigDecimal discountPrice=BigDecimal.ZERO;// 优惠价格 默认0 指优惠了多少单价

    private BigDecimal discountAmount=BigDecimal.ZERO;// 优惠数量 指优惠了多少数量

    private BigDecimal discountMoney=BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
    
    private BigDecimal discountPriceMoney=BigDecimal.ZERO;// 非价格以外的优惠。优惠金额 指优惠了多少金额
    private BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格
    private BigDecimal discountCaclPrice 	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice

    private PromInfoDTO promInfoDTO;
    
    private List<PromInfoDTO> promInfoDTOList;// 满足促销列表

    private PromTypeMsgResponseDTO promTypeMsgResponseDTO;// 优惠提醒信息 ifFulfillProm==true 取fulfilMsg // 否则取noFulfilMsg

    private boolean ifFulfillProm;// true 满足促销promId false不满足促销ID
    
    private List<PromGiftDTO> promGiftDTOList;//订单赠品列表
    
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        if(discountPrice==null){
            this.discountPrice=BigDecimal.ZERO;
        }else{
            this.discountPrice = discountPrice.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        if(discountAmount==null){
            this.discountAmount=BigDecimal.ZERO;
        }else{
            this.discountAmount = discountAmount.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        if(discountMoney==null){
            this.discountMoney=BigDecimal.ZERO;
        }else{
            this.discountMoney = discountMoney.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

    public PromInfoDTO getPromInfoDTO() {
        return promInfoDTO;
    }

    public void setPromInfoDTO(PromInfoDTO promInfoDTO) {
        this.promInfoDTO = promInfoDTO;
    }

    public List<PromInfoDTO> getPromInfoDTOList() {
        return promInfoDTOList;
    }

    public void setPromInfoDTOList(List<PromInfoDTO> promInfoDTOList) {
        this.promInfoDTOList = promInfoDTOList;
    }

    public PromTypeMsgResponseDTO getPromTypeMsgResponseDTO() {
        return promTypeMsgResponseDTO;
    }

    public void setPromTypeMsgResponseDTO(PromTypeMsgResponseDTO promTypeMsgResponseDTO) {
        this.promTypeMsgResponseDTO = promTypeMsgResponseDTO;
    }

    public boolean isIfFulfillProm() {
        return ifFulfillProm;
    }

    public void setIfFulfillProm(boolean ifFulfillProm) {
        this.ifFulfillProm = ifFulfillProm;
    }

    public List<PromGiftDTO> getPromGiftDTOList() {
        return promGiftDTOList;
    }

    public void setPromGiftDTOList(List<PromGiftDTO> promGiftDTOList) {
        this.promGiftDTOList = promGiftDTOList;
    }

    public BigDecimal getDiscountPriceMoney() {
        return discountPriceMoney;
    }

    public void setDiscountPriceMoney(BigDecimal discountPriceMoney) {
        if(discountPriceMoney==null){
            this.discountPriceMoney=BigDecimal.ZERO;
        }else{
            this.discountPriceMoney = discountPriceMoney.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

	public BigDecimal getDiscountFinalPrice() {
		return discountFinalPrice;
	}

	public void setDiscountFinalPrice(BigDecimal discountFinalPrice) {
		this.discountFinalPrice = discountFinalPrice;
	}

	public BigDecimal getDiscountCaclPrice() {
		return discountCaclPrice;
	}

	public void setDiscountCaclPrice(BigDecimal discountCaclPrice) {
		this.discountCaclPrice = discountCaclPrice;
	}


}
