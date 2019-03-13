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


}
