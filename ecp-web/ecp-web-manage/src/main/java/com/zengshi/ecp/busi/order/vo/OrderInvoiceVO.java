package com.zengshi.ecp.busi.order.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceCommRequest;

public class OrderInvoiceVO extends EcpBasePageReqVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String invoiceType;
	
	private String orderId;
	
	private ROrdInvoiceCommRequest ordInvoiceCommRequest;

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public ROrdInvoiceCommRequest getOrdInvoiceCommRequest() {
		return ordInvoiceCommRequest;
	}

	public void setOrdInvoiceCommRequest(ROrdInvoiceCommRequest ordInvoiceCommRequest) {
		this.ordInvoiceCommRequest = ordInvoiceCommRequest;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
