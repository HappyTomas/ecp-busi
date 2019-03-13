/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoReq.java 
 * Package Name:com.zengshi.ecp.app.req 
 * Date:2016-2-22下午6:52:57 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.resp;

import java.util.List;

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
public class Coup001Resp extends IBody {
    
	List<CoupDetailResp> respList;
	// 我的优惠券总数量
	Long coupCount;
	// 1:可使用 2:已使用 0:已过期
	Long coupCount_0;
	Long coupCount_1;
	Long coupCount_2;
	
	
	public Long getCoupCount_0() {
		return coupCount_0;
	}

	public void setCoupCount_0(Long coupCount_0) {
		this.coupCount_0 = coupCount_0;
	}

	public Long getCoupCount_1() {
		return coupCount_1;
	}

	public void setCoupCount_1(Long coupCount_1) {
		this.coupCount_1 = coupCount_1;
	}

	public Long getCoupCount_2() {
		return coupCount_2;
	}

	public void setCoupCount_2(Long coupCount_2) {
		this.coupCount_2 = coupCount_2;
	}

	public Long getCoupCount() {
		return coupCount;
	}

	public void setCoupCount(Long coupCount) {
		this.coupCount = coupCount;
	}

	public List<CoupDetailResp> getRespList() {
		return respList;
	}

	public void setRespList(List<CoupDetailResp> respList) {
		this.respList = respList;
	}

	
  	
  	
}

