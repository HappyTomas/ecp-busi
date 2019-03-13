package com.zengshi.ecp.goods.dubbo.dto.price;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 单品返回 DTO<br>
 * Date:2015年10月9日下午2:17:48 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsPriceCalRespDTO extends BaseResponseDTO {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;

    /**
     * 单品id
     */
    private Long skuId;

    /**
     * 商品编码
     */
    private Long gdsId;

    /**
     * 单品名称（与商品同值）
     */
    private String gdsName;

    /**
     * 当前计算基础价格
     */
    private Long basePrice;

    /**
     * 当前计算购买价格
     */
    private Long buyPrice;

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

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
    }

    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

}
