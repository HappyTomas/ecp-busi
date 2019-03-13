package com.zengshi.ecp.busi.goods.gdsdetail.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsEvalVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1206345864081115181L;
    /**
     * 商品编码（id）
     */
    public Long gdsId;
    
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    
}

