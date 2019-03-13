package com.zengshi.ecp.busi.seller.goods.vo;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsSkuVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3357489957794724974L;
    /**
     * 单品ID
     */
    @NotNull(message="{goods.gdsSkuVO.skuId.null.error}")
    private Long skuId;
    /**
     * 店铺ID
     */
    @NotNull(message="{goods.gdsSkuVO.shopId.null.error}")
    private Long shopId;
    /**
     * 商品ID
     */
    @NotNull(message="{goods.gdsSkuVO.gdsId.null.error}")
    private Long gdsId;
    /**
     * 单品状态
     */
    @NotNull(message="{goods.gdsSkuVO.skuStatus.null.error}")
    private String skuStatus;
    
    private String operateFlag;
    
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public String getSkuStatus() {
        return skuStatus;
    }
    public void setSkuStatus(String skuStatus) {
        this.skuStatus = skuStatus;
    }
    public String getOperateFlag() {
        return operateFlag;
    }
    public void setOperateFlag(String operateFlag) {
        this.operateFlag = operateFlag;
    }
    
    
}

