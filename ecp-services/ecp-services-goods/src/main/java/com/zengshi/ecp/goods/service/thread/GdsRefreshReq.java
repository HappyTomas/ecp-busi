/** 
 * Project Name:pmph-services-server 
 * File Name:GdsRefreshReq.java 
 * Package Name:com.zengshi.ecp.pmph.service.busi.impl.dataimport.thread 
 * Date:2017年8月11日上午11:12:37 
 * Copyright (c) 2017, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.thread;

/**
 * Title: ECP <br>
 * Project Name:pmph-services-server <br>
 * Description: 商品重刷索引对象<br>
 * Date:2017年8月11日上午11:12:37  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsRefreshReq {
    
    private Long gdsId;
    private Long catlogId;
    private Long skuId;
    
    
   
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public Long getCatlogId() {
        return catlogId;
    }
    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
    }
    
    
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public GdsRefreshReq(Long gdsId, Long catlogId, Long skuId) {
        super();
        this.gdsId = gdsId;
        this.catlogId = catlogId;
        this.skuId = skuId;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GdsRefreshReq [gdsId=");
        builder.append(gdsId);
        builder.append(", catlogId=");
        builder.append(catlogId);
        builder.append(", skuId=");
        builder.append(skuId);
        builder.append("]");
        return builder.toString();
    }
   
    
    
    
    

}

