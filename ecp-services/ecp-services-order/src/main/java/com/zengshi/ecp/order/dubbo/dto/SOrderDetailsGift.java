package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class SOrderDetailsGift implements Serializable{
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1631662420275146203L;

    private Long giftId;
    
    private String giftName;
    
    private Long giftCount;

	public Long getGiftId() {
		return giftId;
	}

	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public Long getGiftCount() {
		return giftCount;
	}

	public void setGiftCount(Long giftCount) {
		this.giftCount = giftCount;
	}


    
    
}

