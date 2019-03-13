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
public class ScoreDetailVO implements Serializable{

    private String orderId;
    
    private String scoreTypeName;
    
    private String createTime;

    private String consumeScore;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getScoreTypeName() {
		return scoreTypeName;
	}

	public void setScoreTypeName(String scoreTypeName) {
		this.scoreTypeName = scoreTypeName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getConsumeScore() {
		return consumeScore;
	}

	public void setConsumeScore(String consumeScore) {
		this.consumeScore = consumeScore;
	}

}

