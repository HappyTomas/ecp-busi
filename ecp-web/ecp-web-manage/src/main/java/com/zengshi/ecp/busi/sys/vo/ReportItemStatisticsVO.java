package com.zengshi.ecp.busi.sys.vo;

import java.io.Serializable;
import java.util.List;

public class ReportItemStatisticsVO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4139535181270064752L;

	private String serviceMsg;
	
	private List<ReportTupleItemVO> reportItemRespDTOs;
	
	private int itemCount;
	
	private String serviceState;

	public String getServiceMsg() {
		return serviceMsg;
	}

	public void setServiceMsg(String serviceMsg) {
		this.serviceMsg = serviceMsg;
	}

	public List<ReportTupleItemVO> getReportItemRespDTOs() {
		return reportItemRespDTOs;
	}

	public void setReportItemRespDTOs(List<ReportTupleItemVO> reportItemRespDTOs) {
		this.reportItemRespDTOs = reportItemRespDTOs;
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
	
}
