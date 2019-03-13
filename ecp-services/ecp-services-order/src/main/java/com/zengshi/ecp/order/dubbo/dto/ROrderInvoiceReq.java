package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ROrderInvoiceReq extends BaseInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String invoiceType;
	
	private String orderId;
	
	private ROrdInvoiceCommRequest ordInvoiceCommRequest;
	
	private ROrdInvoiceTaxRequest ordInvoiceTaxRequest;

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

	public ROrdInvoiceTaxRequest getOrdInvoiceTaxRequest() {
		return ordInvoiceTaxRequest;
	}

	public void setOrdInvoiceTaxRequest(ROrdInvoiceTaxRequest ordInvoiceTaxRequest) {
		this.ordInvoiceTaxRequest = ordInvoiceTaxRequest;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
