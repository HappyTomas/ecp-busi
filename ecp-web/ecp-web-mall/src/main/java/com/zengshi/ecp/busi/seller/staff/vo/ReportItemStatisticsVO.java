package com.zengshi.ecp.busi.seller.staff.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReportItemStatisticsVO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4139535181270064752L;

	private String serviceMsg; //服务响应描述
	
	private int itemCount; //返回的总记录数
	
	private String serviceState; //服务响应状态：0000-正常，1001-参数校验错误，2001-服务异常
	
	private Long shopId; //店铺ID
	
	private List<DailyTrade> tradeList; //返回的日交易数据列表
	
	public String getServiceMsg() {
		return serviceMsg;
	}

	public void setServiceMsg(String serviceMsg) {
		this.serviceMsg = serviceMsg;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public String getServiceState() {
		return serviceState;
	}

	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public DailyTrade newDailyTradeInst(String dataDate,String orderAmount, String paidAmount){
		return new DailyTrade(dataDate,orderAmount, paidAmount);
	}
	
	/**
	 * 
	 * Title: ECP <br>
	 * Project Name:ecp-web-mall <br>
	 * Description: <br>
	 * Date:2016年6月1日上午11:26:22  <br>
	 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
	 * 
	 * @author linby
	 * @version ReportItemStatisticsVO 
	 * @since JDK 1.7
	 */
	public class DailyTrade {
		
		private String dataDate;
		private String orderAmount;
		private String paidAmount;
		
		public DailyTrade(){
			
		}
		public DailyTrade(String dataDate,String orderAmount, String paidAmount){
			this.dataDate = dataDate;
			this.orderAmount = orderAmount;
			this.paidAmount = paidAmount;
		}
		
		public String getDataDate() {
			return dataDate;
		}
		public void setDataDate(String dataDate) {
			this.dataDate = dataDate;
		}
		public String getOrderAmount() {
			return orderAmount;
		}
		public void setOrderAmount(String orderAmount) {
			this.orderAmount = orderAmount;
		}
		public String getPaidAmount() {
			return paidAmount;
		}
		public void setPaidAmount(String paidAmount) {
			this.paidAmount = paidAmount;
		}
	}


	public List<DailyTrade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<DailyTrade> tradeList) {
		this.tradeList = tradeList;
	}
	
}
