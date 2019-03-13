package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

public class Ord009Resp extends IBody {
    
    /** 
     * staffId:当前用户ID. 
     * @since JDK 1.6 
     */ 
    private Long staffId;
    
    /** 
     * redisKey:数据缓存中的Key. 
     * @since JDK 1.6 
     */ 
    private String redisKey;

    /**
     * 是否有异常标识
     */
    private String exceptionFlag;

    private String exceptionContent;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public String getExceptionFlag() {
        return exceptionFlag;
    }

    public void setExceptionFlag(String exceptionFlag) {
        this.exceptionFlag = exceptionFlag;
    }

    public String getExceptionContent() {
        return exceptionContent;
    }

    public void setExceptionContent(String exceptionContent) {
        this.exceptionContent = exceptionContent;
    }
}

