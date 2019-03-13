package com.zengshi.ecp.order.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class OrdExpressDetailResp extends BaseResponseDTO {
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private Long id;

	    private String orderId;

	    private String expressId;

	    private String status;

	    private String isCheck;

	    private String expressCode;

	    private String context;

	    private Timestamp expressTime;

	    private String areaCode;

	    private String areaName;

	    private Timestamp createTime;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getIsCheck() {
			return isCheck;
		}

		public void setIsCheck(String isCheck) {
			this.isCheck = isCheck;
		}

		public String getExpressCode() {
			return expressCode;
		}

		public void setExpressCode(String expressCode) {
			this.expressCode = expressCode;
		}

		public String getContext() {
			return context;
		}

		public void setContext(String context) {
			this.context = context;
		}

		public Timestamp getExpressTime() {
			return expressTime;
		}

		public void setExpressTime(Timestamp expressTime) {
			this.expressTime = expressTime;
		}

		public String getAreaCode() {
			return areaCode;
		}

		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public Timestamp getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
		}
}
