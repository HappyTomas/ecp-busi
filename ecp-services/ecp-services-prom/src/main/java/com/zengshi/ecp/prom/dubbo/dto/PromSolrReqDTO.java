package com.zengshi.ecp.prom.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-11-19 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromSolrReqDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private String gdsStatus;//商品状态
    private String tableName;//表名
    private String className;//类名
    private Long gdsId;//商品编码
    //private Long catlogId;//目录编码
    private Long catgCode;//分类编码
    private Long promId;//促销编码
    private Long staffId;//操作人
    private Long shopId;//店铺编码
    private Long siteId;//站点编码
    private String sendRange;//发送范围 1全量发送 siteId非空  2分类发送 catLogId非空  3商品发送 gdsId非空
    
    public String getGdsStatus() {
        return gdsStatus;
    }
    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public Long getCatgCode() {
        return catgCode;
    }
    public void setCatgCode(Long catgCode) {
        this.catgCode = catgCode;
    }
/*    public Long getCatlogId() {
        return catlogId;
    }
    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
    }*/
    public Long getPromId() {
        return promId;
    }
    public void setPromId(Long promId) {
        this.promId = promId;
    }
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Long getSiteId() {
        return siteId;
    }
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    public String getSendRange() {
        return sendRange;
    }
    public void setSendRange(String sendRange) {
        this.sendRange = sendRange;
    }
  
}
