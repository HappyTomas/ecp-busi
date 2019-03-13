package com.zengshi.ecp.order.dubbo.dto.pay;
import java.io.Serializable;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 查询支付结果请求抽象类<br>
 * Date:2015年10月10日上午10:25:17  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public abstract class BasePayResultQueryResponse implements Serializable {
//   private String responseMessge;
   
   public String toString() {
		return JSONObject.fromObject(this).toString();
   }
   
   public void buildSelf(final Map<String,String> responseMap) throws Exception{
		
   }

}