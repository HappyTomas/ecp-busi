package com.zengshi.ecp.order.dubbo.dto.pay;

import java.io.Serializable;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付回调对象<br>
 * Date:2015年10月10日上午10:28:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class PayResponseData implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 3499782328849642786L;
	private BasePayResponse basePayResponse;
	public BasePayResponse getBasePayResponse() {
		return basePayResponse;
	}
	public void setBasePayResponse(BasePayResponse basePayResponse) {
		this.basePayResponse = basePayResponse;
	}

}