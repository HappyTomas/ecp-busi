/** 
 * Project Name:ecp-web-mall 
 * File Name:CustAddrVO.java 
 * Package Name:com.zengshi.ecp.busi.staff.seller.vo 
 * Date:2015年9月18日下午8:32:50 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.buyer.vo;

import java.io.Serializable;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月18日下午8:32:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public class MsgInsiteVO implements Serializable{

    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 1L;

	private String msgName;
    
    private String msgContext;
    
    private String createTime;

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getMsgContext() {
		return msgContext;
	}

	public void setMsgContext(String msgContext) {
		this.msgContext = msgContext;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


}

