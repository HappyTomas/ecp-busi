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
import java.sql.Timestamp;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月18日下午8:32:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhuqr
 * @version  
 * @since JDK 1.6 
 */
public class ScoreChangeRecodeVO implements Serializable{

    private Long discountMoney;
    private Long score;
    private String picUrl;
    private String gdsUrl;
    private String picId;
    private String gdsName;
    private Timestamp orderTime;
	public Long getDiscountMoney() {
		return discountMoney;
	}
	public void setDiscountMoney(Long discountMoney) {
		this.discountMoney = discountMoney;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getGdsUrl() {
		return gdsUrl;
	}
	public void setGdsUrl(String gdsUrl) {
		this.gdsUrl = gdsUrl;
	}
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public String getGdsName() {
		return gdsName;
	}
	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
    
    
}

