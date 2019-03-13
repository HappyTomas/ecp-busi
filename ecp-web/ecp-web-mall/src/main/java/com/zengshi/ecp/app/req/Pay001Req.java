package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Pay001Req extends IBody {
    
    /** 
     * redisKey:缓存中的key. 
     * @since JDK 1.6 
     */ 
    private String redisKey;

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }
    
}

