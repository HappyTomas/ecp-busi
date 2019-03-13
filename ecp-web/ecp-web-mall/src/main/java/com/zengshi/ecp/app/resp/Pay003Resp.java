package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Pay003Resp extends IBody {
    
    /** 
     * redisKey:缓存中的key. 
     * @since JDK 1.6 
     */ 
    private String redisKey;
    
    /** 
     * pay00301Resps:支付店铺列表. 
     * @since JDK 1.6 
     */ 
    private List<Pay00301Resp> pay00301Resps;
    
    // 是否合并支付
    private boolean isMergePay;

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public List<Pay00301Resp> getPay00301Resps() {
        return pay00301Resps;
    }

    public void setPay00301Resps(List<Pay00301Resp> pay00301Resps) {
        this.pay00301Resps = pay00301Resps;
    }

	public boolean isMergePay() {
		return isMergePay;
	}

	public void setMergePay(boolean isMergePay) {
		this.isMergePay = isMergePay;
	}
    
}

