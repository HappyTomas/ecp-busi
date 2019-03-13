package com.zengshi.ecp.goods.dubbo.dto.stock;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 库存信息返回Resp对象<br>
 * Date:2015年10月9日下午2:14:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsStockInfoRespDTO extends BaseResponseDTO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 9197036868497822424L;

    /**
     * 适用地区 省份
     */
    private String adaptProvince;
    
    /**
     * 适用地区 地市
     */
    private String adaptCity;
    
    /**
     * 适用地区 国家
     */
    private String adaptCountry;
    
    /**
     * 可用库存
     */
    private Long availCount;
    
    /**
     * 仓库编码
     */
    private String repCode;

    /**
     * 仓库类型  00 买家仓库   01卖家总仓  02卖家分仓
     */
    private String repType;
    
    /**
     * 单品编码
     */
    private long skuId;
    
    /**
     * 库存编码
     */
    private Long stockId;

    public String getAdaptProvince() {
        return adaptProvince;
    }

    public void setAdaptProvince(String adaptProvince) {
        this.adaptProvince = adaptProvince;
    }

    public String getAdaptCity() {
        return adaptCity;
    }

    public void setAdaptCity(String adaptCity) {
        this.adaptCity = adaptCity;
    }

    public String getAdaptCountry() {
        return adaptCountry;
    }

    public void setAdaptCountry(String adaptCountry) {
        this.adaptCountry = adaptCountry;
    }

    public Long getAvailCount() {
        return availCount;
    }

    public void setAvailCount(Long availCount) {
        this.availCount = availCount;
    }

    public String getRepCode() {
        return repCode;
    }

    public void setRepCode(String repCode) {
        this.repCode = repCode;
    }


    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
}

