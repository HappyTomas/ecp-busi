/**
 * RSumbitMainReqVO.java	  V1.0   2015-10-9 下午8:06:31
 *
 * Copyright 2015 AsiaInfo Co.,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.zengshi.ecp.busi.order.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceCommRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceTaxRequest;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;

import java.util.List;

public class RSumbitMainReqVO extends EcpBasePageReqVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;


    // 运费
    private Long realExpressFee;

    private List<AcctInfoResDTO> ordAcctInfoList;
    
    // 实付金额
    private Long realMoney;   
    
    // 店铺优惠金额
    private Long shopDiscountMoney;


    // 单品优惠金额（针对商品而言）
    private Long discountMoney;

    // cartId
    private Long cartId;

    // 发票类型---提交订单时候使用、
    private String invoiceType;

    // 普票信息---提交订单时候使用、
    private ROrdInvoiceCommRequest rOrdInvoiceCommRequest;

    // 增票信息---提交订单时候使用、
    private ROrdInvoiceTaxRequest rOrdInvoiceTaxRequest;
    
    //配送方式
    private String deliverType;

    //优惠券使用
    private List<CoupCheckBeanRespVO> coupCheckBean;
    
    public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType;
    }

    public List<AcctInfoResDTO> getOrdAcctInfoList() {
        return ordAcctInfoList;
    }

    public void setOrdAcctInfoList(List<AcctInfoResDTO> ordAcctInfoList) {
        this.ordAcctInfoList = ordAcctInfoList;
    }

    public Long getRealExpressFee() {
        return realExpressFee;
    }

    public void setRealExpressFee(Long realExpressFee) {
        this.realExpressFee = realExpressFee;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public ROrdInvoiceCommRequest getrOrdInvoiceCommRequest() {
        return rOrdInvoiceCommRequest;
    }

    public void setrOrdInvoiceCommRequest(ROrdInvoiceCommRequest rOrdInvoiceCommRequest) {
        this.rOrdInvoiceCommRequest = rOrdInvoiceCommRequest;
    }

    public ROrdInvoiceTaxRequest getrOrdInvoiceTaxRequest() {
        return rOrdInvoiceTaxRequest;
    }

    public void setrOrdInvoiceTaxRequest(ROrdInvoiceTaxRequest rOrdInvoiceTaxRequest) {
        this.rOrdInvoiceTaxRequest = rOrdInvoiceTaxRequest;
    }

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

	public Long getShopDiscountMoney() {
		return shopDiscountMoney;
	}

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }

    public void setShopDiscountMoney(Long shopDiscountMoney) {
		this.shopDiscountMoney = shopDiscountMoney;
	}

    public List<CoupCheckBeanRespVO> getCoupCheckBean() {
        return coupCheckBean;
    }

    public void setCoupCheckBean(List<CoupCheckBeanRespVO> coupCheckBean) {
        this.coupCheckBean = coupCheckBean;
    }
}
