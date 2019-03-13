package com.zengshi.ecp.busi.goods.vo;

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
    /**
     * 单品编码
     */
    public Long skuId;
    /**
     * 评价的等级。0：全部；1：好评；2：中评；3：差评
     */
    public String evalLeval;
    /**
     * 页数
     */
    public int page;
    
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
    public String getEvalLeval() {
        return evalLeval;
    }
    public void setEvalLeval(String evalLeval) {
        this.evalLeval = evalLeval;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    
}

