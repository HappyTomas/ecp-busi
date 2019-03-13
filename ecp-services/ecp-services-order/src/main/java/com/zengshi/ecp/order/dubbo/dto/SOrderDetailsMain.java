package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class SOrderDetailsMain  implements Serializable {
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 2289919620555086554L;
    /*订单详情主订单表相关字段*/
    /** 
     * orderType:订单类型. 
     * @since JDK 1.6 
     */ 
    private String orderType;
    /** 
     * status:订单状态. 
     * @since JDK 1.6 
     */ 
    private String status;
    /** 
     * orderMoney:商品总金额. 
     * @since JDK 1.6 
     */ 
    private Long orderMoney;
    /** 
     * realExpressFee:运费. 
     * @since JDK 1.6 
     */ 
    private Long realExpressFee;
    /** 
     * realMoney:应付金额. 
     * @since JDK 1.6 
     */ 
    private Long realMoney;
    /** 
     * orderScore:订单总积分. 
     * @since JDK 1.6 
     */ 
    private Long orderScore;
    /** 
     * payType:付款方式. 
     * @since JDK 1.6 
     */ 
    private String payType;
    /** 
     * payTime:付款时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp payTime;
    
    /** 
     * payFlag:支付状态. 
     * @since JDK 1.6 
     */ 
    private String payFlag;
    
    /** 
     * orderTime:下单时间. 
     * @since JDK 1.6 
     */ 
    private Timestamp orderTime;
    
    /** 
     * shopId:卖家ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    
    /** 
     * shopName:店铺名称. 
     * @since JDK 1.6 
     */ 
    private String shopName;
    
    /** 
     * siteId:站点. 
     * @since JDK 1.6 
     */ 
    private Long siteId;
    
    /** 
     * contactName:收货人. 
     * @since JDK 1.6 
     */ 
    private String contactName;
    /** 
     * chnlAddress:地址. 
     * @since JDK 1.6 
     */ 
    private String chnlAddress;
    /** 
     * contactPhone:手机号. 
     * @since JDK 1.6 
     */ 
    private String contactPhone;
    /** 
     * contactNumber:固定电话. 
     * @since JDK 1.6 
     */ 
    private String contactNumber;
    
    /** 
     * invoiceType:发票类型. 
     * @since JDK 1.6 
     */ 
    private String invoiceType;
    /** 
     * contactNumber:配送方式
     * @since JDK 1.6 
     */ 
    private String dispatchType;
    
    /** 
     * contactNumber:买家留言
     * @since JDK 1.6 
     */
    private String buyerMsg;
    
    /** 
     * invoiceType:是否开票. 
     * @since JDK 1.6 
     */ 
    private String billingFlag;    
    
    /** 
     * sendInvoiceType:发票寄送方式. 
     * @since JDK 1.6 
     */ 
    private String sendInvoiceType;

    /** 
     * invoiceExpressNo:发票寄送快递单号. 
     * @since JDK 1.6 
     */ 
    private String invoiceExpressNo;
    
    /** 
     * id:订单号. 
     * @since JDK 1.6 
     */ 
    private String id;
    
    /** 
     * orderAmount:订单订购数量. 
     * @since JDK 1.6 
     */ 
    private Long orderAmount;
    
    /** 
     * basicMoney:订单码洋. 
     * @since JDK 1.6 
     */ 
    private Long basicMoney;
    
    /** 
     * payWay:支付通道. 
     * @since JDK 1.6 
     */ 
    private String payWay;
    
    /**
     * createStaff：创建人
     */
    private Long createStaff;
    
    /**
     * deliverDate 发货时间
     */
    private Timestamp deliverDate;
    
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String cityCode;
    /**
     * 县
     */
    private String countyCode;
    /**
     * 卖家留言
     * 
     */
    private String sellerMsg;
    
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Long getBasicMoney() {
		return basicMoney;
	}

	public void setBasicMoney(Long basicMoney) {
		this.basicMoney = basicMoney;
	}

	public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendInvoiceType() {
        return sendInvoiceType;
    }

    public void setSendInvoiceType(String sendInvoiceType) {
        this.sendInvoiceType = sendInvoiceType;
    }

    public String getInvoiceExpressNo() {
        return invoiceExpressNo;
    }

    public void setInvoiceExpressNo(String invoiceExpressNo) {
        this.invoiceExpressNo = invoiceExpressNo;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getChnlAddress() {
        return chnlAddress;
    }

    public void setChnlAddress(String chnlAddress) {
        this.chnlAddress = chnlAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }


    public Long getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(Long orderScore) {
        this.orderScore = orderScore;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }
    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getRealExpressFee() {
        return realExpressFee;
    }
    public void setRealExpressFee(Long realExpressFee) {
        this.realExpressFee = realExpressFee;
    }
    public Long getRealMoney() {
        return realMoney;
    }
    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public Timestamp getPayTime() {
        return payTime;
    }
    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getBuyerMsg() {
        return buyerMsg;
    }

    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg;
    }
    
    public String getBillingFlag() {
        return billingFlag;
    }

    public void setBillingFlag(String billingFlag) {
        this.billingFlag = billingFlag;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

	public Timestamp getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Timestamp deliverDate) {
		this.deliverDate = deliverDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getSellerMsg() {
		return sellerMsg;
	}

	public void setSellerMsg(String sellerMsg) {
		this.sellerMsg = sellerMsg;
	}
    
}

