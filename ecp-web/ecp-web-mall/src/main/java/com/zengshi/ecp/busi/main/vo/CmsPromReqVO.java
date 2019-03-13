/** 
 * Project Name:ecp-web-manage Maven Webapp 
 * File Name:CmsInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.cms.vo 
 * Date:2015-8-14下午3:14:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.main.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月2日上午9:38:16  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public class CmsPromReqVO extends EcpBasePageReqVO implements Serializable {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3966936688015434267L;
    /** 
     * 站点ID. 
     */ 
    private Long siteId;
    /** 
     * 模板ID. 
     */ 
    private Long templateId;
    /** 
     * 内容位置ID. 
     */ 
    private Long placeId;
    /** 
     * 楼层广告内容位置ID. 
     */ 
    private Long adPlaceId;
    /** 
     * 楼层D . 
     */ 
    private Long floorId;
    /** 
     * 页签ID. 
     */ 
    private Long tabId;
    /** 
     * 楼层中优惠券显示个数. 
     */ 
    private Integer coupSize;
    /** 
     * 楼层中商品显示个数. 
     */ 
    private Integer gdsSize;
    /** 
     * 楼层中页签显示个数. 
     */
    private Integer tabSize;
    /** 
     * 内容位置宽. 
     */
    private Long placeWidth;
    /** 
     * 内容位置高. 
     */
    private Long placeHeight;
    /** 
     * 状态. 0，无效 1，有效 
     */
    private String status;
    /** 
     * 楼层类型 0，纯优惠券  1纯商品  2优惠券加商品
     */
    private String floorType;
    /** 
     * 组件返回VM地址
     */
    private String returnUrl;
    
    public Long getSiteId() {
        return siteId;
    }
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    public Long getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
    public Long getPlaceId() {
        return placeId;
    }
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
    public Long getAdPlaceId() {
        return adPlaceId;
    }
    public void setAdPlaceId(Long adPlaceId) {
        this.adPlaceId = adPlaceId;
    }
    public Long getFloorId() {
        return floorId;
    }
    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }
    public Long getTabId() {
        return tabId;
    }
    public void setTabId(Long tabId) {
        this.tabId = tabId;
    }
    public Integer getCoupSize() {
        return coupSize;
    }
    public void setCoupSize(Integer coupSize) {
        this.coupSize = coupSize;
    }
    public Integer getGdsSize() {
        return gdsSize;
    }
    public void setGdsSize(Integer gdsSize) {
        this.gdsSize = gdsSize;
    }
    public Integer getTabSize() {
        return tabSize;
    }
    public void setTabSize(Integer tabSize) {
        this.tabSize = tabSize;
    }
    public Long getPlaceWidth() {
        return placeWidth;
    }
    public void setPlaceWidth(Long placeWidth) {
        this.placeWidth = placeWidth;
    }
    public Long getPlaceHeight() {
        return placeHeight;
    }
    public void setPlaceHeight(Long placeHeight) {
        this.placeHeight = placeHeight;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFloorType() {
        return floorType;
    }
    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }
    public String getReturnUrl() {
        return returnUrl;
    }
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    
    
}

