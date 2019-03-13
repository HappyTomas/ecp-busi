package com.zengshi.ecp.busi.main.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**页面组件请求服务VO
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月21日下午5:49:47 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
public class ComponentReqVO extends EcpBasePageReqVO implements Serializable {

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
     * 内容位置中元素显示个数. 
     */ 
    private Integer placeSize;
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
     * 位置类型
     */
    private String placeType;
    /** 
     * 推荐类型
     */
    private String recommendType;
    
    /** 
     * 分类菜单类型
     */
    private String menuType;
    
    /** 
     * 分类ID
     */
    private String catalogId;
    
    /**
     * 返回vm的业务路径
     */
    private String path;
    /**
     * 版本类型
     * 0：核心演示
     * 1：人卫
     */
    private String versionType;
    
    private Long shopId;
    /**
     * 商品分类
     */
    private String gdsCategory;
    /** 
     * 楼层类型 0，纯优惠券  1纯商品  2优惠券加商品
     */
    private String floorType;
    /** 
     * 楼层中优惠券显示个数. 
     */ 
    private Integer coupSize;
    /**
     * 请求返回的vm路径
     */
    private String returnUrl;
    /**
     * 展示方式。一行多少个商品
     */
    private String showWay;
    /**
     * 是否取行为分析
     */
    private String ifAnalys;
    
    private static final long serialVersionUID = 1L;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getTemplateId() {
        return templateId;
    }
    
    public Long getTabId() {
        return tabId;
    }

    public void setTabId(Long tabId) {
        this.tabId = tabId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getPlaceWidth() {
        return placeWidth;
    }

    public void setPlaceWidth(Long placeWidth) {
        this.placeWidth = placeWidth;
    }

    public Integer getPlaceSize() {
        return placeSize;
    }

    public void setPlaceSize(Integer placeSize) {
        this.placeSize = placeSize;
    }

    public Long getPlaceHeight() {
        return placeHeight;
    }

    public void setPlaceHeight(Long placeHeight) {
        this.placeHeight = placeHeight;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
    
    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }
    
    public String getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
    
    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
    
    public Long getAdPlaceId() {
        return adPlaceId;
    }

    public void setAdPlaceId(Long adPlaceId) {
        this.adPlaceId = adPlaceId;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getGdsCategory() {
        return gdsCategory;
    }

    public void setGdsCategory(String gdsCategory) {
        this.gdsCategory = gdsCategory;
    }

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public Integer getCoupSize() {
        return coupSize;
    }

    public void setCoupSize(Integer coupSize) {
        this.coupSize = coupSize;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getShowWay() {
        return showWay;
    }

    public void setShowWay(String showWay) {
        this.showWay = showWay;
    }

    public String getIfAnalys() {
        return ifAnalys;
    }

    public void setIfAnalys(String ifAnalys) {
        this.ifAnalys = ifAnalys;
    }

}
