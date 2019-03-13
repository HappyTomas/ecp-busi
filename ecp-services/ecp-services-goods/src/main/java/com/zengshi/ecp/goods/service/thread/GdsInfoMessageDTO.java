/** 
 * Project Name:xhs-services-extend-server 
 * File Name:GdsInfoMessageDTO.java 
 * Package Name:com.zengshi.ecp.xhsextend.facade.impl.eventual.thread 
 * Date:2017年3月27日上午10:37:26 
 * Copyright (c) 2017, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.thread;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:xhs-services-extend-server <br>
 * Description: 稿件消息服务，用于异步线程执行。<br>
 * Date:2017年3月27日上午10:37:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsInfoMessageDTO extends BaseInfo{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7208658971851950947L;
    // 单品维度ID。
    private List<Long> skuIds;
    // 店铺ID.
    private Long shopId;
    
    private Long gdsId;
    
    //private List<Long> specialSkuIds;
    
    private Long gdsTypeId;
    
    private String gdsStatus = GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES;
    
    /**
     * 只查询对应目录的产品目录（查询用）
     */
    private Long catlogId; 
    /**
     * 是否发送促销索引消息.
     */
    private boolean sendPromMsg = false;
    
    public List<Long> getSkuIds() {
        return skuIds;
    }
    public void setSkuIds(List<Long> skuIds) {
        this.skuIds = skuIds;
    }
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public Long getGdsTypeId() {
        return gdsTypeId;
    }
    public void setGdsTypeId(Long gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }
    /*public List<Long> getSpecialSkuIds() {
        return specialSkuIds;
    }
    public void setSpecialSkuIds(List<Long> specialSkuIds) {
        this.specialSkuIds = specialSkuIds;
    }*/
    public String getGdsStatus() {
        return gdsStatus;
    }
    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }
    
	public Long getCatlogId() {
		return catlogId;
	}
	public void setCatlogId(Long catlogId) {
		this.catlogId = catlogId;
	}
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
    
    
    
    public boolean isSendPromMsg() {
        return sendPromMsg;
    }
    public void setSendPromMsg(boolean sendPromMsg) {
        this.sendPromMsg = sendPromMsg;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GdsInfoMessageDTO [skuIds=");
        builder.append(skuIds);
        builder.append(", shopId=");
        builder.append(shopId);
        builder.append(", gdsId=");
        builder.append(gdsId);
        builder.append(", gdsTypeId=");
        builder.append(gdsTypeId);
        builder.append(", gdsStatus=");
        builder.append(gdsStatus);
        builder.append(", catlogId=");
        builder.append(catlogId);
        builder.append(", sendPromMsg=");
        builder.append(sendPromMsg);
        builder.append("]");
        return builder.toString();
    }
    
    
    

}

