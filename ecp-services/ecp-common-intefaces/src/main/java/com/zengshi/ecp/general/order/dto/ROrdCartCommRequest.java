package com.zengshi.ecp.general.order.dto;


import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrdCartCommRequest extends BaseInfo {
    private Long id;

    private String cartType;

    private Long staffId;

    private Long shopId;

    private String shopName;

    private Long promId;
    
    private String source;
    
    private List<AcctInfoCommRequest> acctInfoCommRequest;
    
    private List<ROrdCartItemCommRequest> ordCartItemCommList;
    
    //可使用的优惠券信息 店铺级的
  	private List<CoupCheckBeanRespDTO> coupCheckBean;

    private String orderId;
    
    //购物车实付金额
    private Long payMoney;
    
    //订单积分
    private Long orderScore;
    
    //促销域的入参
    private String custGroupValue; 

    private String custLevelValue; 

    private String channelValue;

    private String areaValue;
    
    private boolean ifFulfillProm;// true 满足促销promId false不满足促销ID
    
    private Timestamp orderTime;

    //优惠码
  	private String coupCode;
  	
    private static final long serialVersionUID = 1L;

    
    public List<CoupCheckBeanRespDTO> getCoupCheckBean() {
		return coupCheckBean;
	}

	public void setCoupCheckBean(List<CoupCheckBeanRespDTO> coupCheckBean) {
		this.coupCheckBean = coupCheckBean;
	}

	public Long getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Long payMoney) {
        this.payMoney = payMoney;
    }

    public boolean isIfFulfillProm() {
        return ifFulfillProm;
    }

    public void setIfFulfillProm(boolean ifFulfillProm) {
        this.ifFulfillProm = ifFulfillProm;
    }

    public Long getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(Long orderScore) {
        this.orderScore = orderScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }




    public List<ROrdCartItemCommRequest> getOrdCartItemCommList() {
        return ordCartItemCommList;
    }

    public void setOrdCartItemCommList(List<ROrdCartItemCommRequest> ordCartItemCommList) {
        this.ordCartItemCommList = ordCartItemCommList;
    }



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustGroupValue() {
        return custGroupValue;
    }

    public void setCustGroupValue(String custGroupValue) {
        this.custGroupValue = custGroupValue;
    }

    public String getCustLevelValue() {
        return custLevelValue;
    }

    public void setCustLevelValue(String custLevelValue) {
        this.custLevelValue = custLevelValue;
    }

    public String getChannelValue() {
        return channelValue;
    }

    public String getCoupCode() {
		return coupCode;
	}

	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}

	public void setChannelValue(String channelValue) {
        this.channelValue = channelValue;
    }

    public String getAreaValue() {
        return areaValue;
    }

    public void setAreaValue(String areaValue) {
        this.areaValue = areaValue;
    }

    public List<AcctInfoCommRequest> getAcctInfoCommRequest() {
        return acctInfoCommRequest;
    }

    public void setAcctInfoCommRequest(List<AcctInfoCommRequest> acctInfoCommRequest) {
        this.acctInfoCommRequest = acctInfoCommRequest;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }



  

    
    
}

