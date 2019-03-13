package com.zengshi.ecp.aip.dubbo.dto;

import com.abc.pay.client.JSON;
import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-aip-server <br>
 * Description: 农行对账响应类<br>
 * Date:2015年12月4日下午8:23:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public class ABCPayQueryOrderResponse extends BaseInfo{

    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -7005885221321742637L;

	private JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }
	
}

