/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoReq.java 
 * Package Name:com.zengshi.ecp.app.req 
 * Date:2016-2-22下午6:52:57 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月7日上午11:40:49  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class Coup003Resp extends IBody {
    
	Long coupCount;

	public Long getCoupCount() {
		return coupCount;
	}

	public void setCoupCount(Long coupCount) {
		this.coupCount = coupCount;
	}

}

