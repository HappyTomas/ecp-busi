package com.zengshi.ecp.busi.order.vo;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainResponse;

public class RPreOrdMainResponseVO extends RPreOrdMainResponse{
	/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6436421027600254625L;

	private String shopName    ;
	private Long agentMoney  ;
	private String express_fax ;
	private String orderType   ;
	List<RPreOrdSubResponseVO> preOrdSubVOList;
	private int payType;

    public int getPayType() {
        return payType;
    }
    public void setPayType(int payType) {
        this.payType = new java.util.Random().nextInt(2);
    }
    public List<RPreOrdSubResponseVO> getPreOrdSubVOList() {
        return preOrdSubVOList;
    }
    public void setPreOrdSubVOList(List<RPreOrdSubResponseVO> preOrdSubVOList) {
        this.preOrdSubVOList = preOrdSubVOList;
    }
    public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Long getAgentMoney() {
		return agentMoney;
	}
	public void setAgentMoney(Long agentMoney) {
		this.agentMoney = agentMoney;
	}
	public String getExpress_fax() {
		return express_fax;
	}
	public void setExpress_fax(String express_fax) {
		this.express_fax = express_fax;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}	

}
