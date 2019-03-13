/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.demo.vo 
 * Date:2015-8-5下午3:02:29 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.prom.auth.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-prom <br>
 * Description: <br>
 * Date:2015-8-18下午3:02:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7
 */
public class PromType4ShopVO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String id;

    private String shopId;
    
    private String shopName;
    
    @NotNull(message="{prom.promType4ShopVO.shopIds.null.error}")
    private String shopIds;
    
    @NotNull(message="{prom.promType4ShopVO.promTypeCode.null.error}")
    private String promTypeCode;

    private String status;
    
    @NotNull(message="{prom.promType4ShopVO.startTime.null.error}")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    @NotNull(message="{prom.promType4ShopVO.endTime.null.error}")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    private Date createTime;

    private Long createStaff;

 
    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopIds() {
        return shopIds;
    }

    public void setShopIds(String shopIds) {
        this.shopIds = shopIds;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    
}

