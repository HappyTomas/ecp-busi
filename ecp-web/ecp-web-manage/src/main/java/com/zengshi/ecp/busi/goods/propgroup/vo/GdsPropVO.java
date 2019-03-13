package com.zengshi.ecp.busi.goods.propgroup.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsPropVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5379677758749452853L;
    
    private Long id;//属性编码
    
    private String propName;//属性名称
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }
    
    
}

