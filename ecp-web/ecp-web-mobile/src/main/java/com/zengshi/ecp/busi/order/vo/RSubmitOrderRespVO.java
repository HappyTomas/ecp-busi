package com.zengshi.ecp.busi.order.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class RSubmitOrderRespVO extends EcpBaseResponseVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3474602671576750531L;
    
    private String onlineKey;
    private String payTypeKey;
    private String appkey;
    public String getOnlineKey() {
        return onlineKey;
    }
    public void setOnlineKey(String onlineKey) {
        this.onlineKey = onlineKey;
    }
    public String getPayTypeKey() {
        return payTypeKey;
    }
    public void setPayTypeKey(String payTypeKey) {
        this.payTypeKey = payTypeKey;
    }
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

}
