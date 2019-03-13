package com.zengshi.ecp.busi.goods.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsCommonCategoryVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -4366481270611907550L;
    
    private Long propId;//属性id
    
    private String propValue;//属性值

    private String categCode;//分类编码
    
    private Long gdsId;//商品编码
    
    private Long skuId;
    
    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public String getCategCode() {
        return categCode;
    }

    public void setCategCode(String categCode) {
        this.categCode = categCode;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
    
    
}

