package com.zengshi.ecp.goods.dubbo.dto.price;

import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdxCriteria;
import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 单品信息入参DTO Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月25日下午5:16:13 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsPriceCalReqDTO extends BaseInfo<GdsSkuInfoShopIdxCriteria> {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;

    /**
     * 单品Id
     */
    private Long skuId;

    /**
     * 商品编码
     */
    private Long gdsId;

    /**
     * 店铺编码
     */
    private Long shopId;

    /**
     * 商品数量
     */
    private Long amount;
    
    /**
     * 积分购买类型
     */
    private String scoreTypeId;
    
    /**
     * 数字印刷标示
     */
    private String prnFlag;
    /**
     * 来源1.商城  2.APP 3.其他
     */
    private String fromSource;
    
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPrnFlag() {
        return prnFlag;
    }

    public void setPrnFlag(String prnFlag) {
        this.prnFlag = prnFlag;
    }

    public String getScoreTypeId() {
        return scoreTypeId;
    }

    public void setScoreTypeId(String scoreTypeId) {
        this.scoreTypeId = scoreTypeId;
    }

    public String getFromSource() {
        return fromSource;
    }

    public void setFromSource(String fromSource) {
        this.fromSource = fromSource;
    }


}
