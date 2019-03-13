package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.butterfly.app.model.IBody;

public class Pay002Resp extends IBody {
    /** 
     * orderId:订单号. 
     * @since JDK 1.6 
     */ 
    private String orderId;
    
    
    /** 
     * mergeTotalRealMoney:金额. 
     * @since JDK 1.6 
     */ 
    private long mergeTotalRealMoney;
    
    /** 
     * pay00101Resps:支付通道相关信息. 
     * @since JDK 1.6 
     */ 
    List<PayWayResponse> wayList;
    
    /** 
     * hour:支付时间. 
     * @since JDK 1.6 
     */ 
    private Long hour;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }



    public List<PayWayResponse> getWayList() {
        return wayList;
    }

    public void setWayList(List<PayWayResponse> wayList) {
        this.wayList = wayList;
    }

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

	public long getMergeTotalRealMoney() {
		return mergeTotalRealMoney;
	}

	public void setMergeTotalRealMoney(long mergeTotalRealMoney) {
		this.mergeTotalRealMoney = mergeTotalRealMoney;
	}
    
}

