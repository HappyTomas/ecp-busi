package com.zengshi.ecp.aip.third.dubbo.dto.req;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class OrderReqDTO extends BaseShopAuthReqDTO{

	private static final long serialVersionUID = -1L;
	
	//订单编码
	private String orderId;

	//验证 基本参数
    public void checkOrderParams() throws BusinessException{
			if(StringUtil.isEmpty(orderId)){
				throw new BusinessException("orderId 参数为空");
			}
			if("taobao".equals(this.getPlatType())){
				  try{
					  long l = Long.parseLong(orderId);
					}catch(Exception e){
						throw new BusinessException("orderId 不能转为数字");
					}
			}
		}
		
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}

