package com.zengshi.ecp.busi.order.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class ROrderMainCheckCarRespVO extends EcpBaseResponseVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3966376567127720606L;
    private String mainHashKey;

    public String getMainHashKey() {
        return mainHashKey;
    }

    public void setMainHashKey(String mainHashKey) {
        this.mainHashKey = mainHashKey;
    }
}