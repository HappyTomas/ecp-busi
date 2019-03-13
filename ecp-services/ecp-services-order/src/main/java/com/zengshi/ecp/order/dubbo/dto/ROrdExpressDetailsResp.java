package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrdExpressDetailsResp extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String orderId;

	private String expressId;
	
	private String expressNo;
	
	private String expressName;
	
	private String deliveryType;
	
	private Timestamp sendDate;
	
	private String status;//当前最新物流状态
	
	private List<OrdExpressDetailResp> ordExpressDetailResps;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExpressId() {
		return expressId;
	}

	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}

	public List<OrdExpressDetailResp> getOrdExpressDetailResps() {
		return ordExpressDetailResps;
	}

	public void setOrdExpressDetailResps(List<OrdExpressDetailResp> ordExpressDetailResps) {
		this.ordExpressDetailResps = ordExpressDetailResps;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
