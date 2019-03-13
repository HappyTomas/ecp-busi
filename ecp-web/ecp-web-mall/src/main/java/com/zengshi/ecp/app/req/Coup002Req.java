/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoReq.java 
 * Package Name:com.zengshi.ecp.app.req 
 * Date:2016-2-22下午6:52:57 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.req;

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
public class Coup002Req extends IBody {
    
	private Long id;

	private Long staffId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	
	
}

