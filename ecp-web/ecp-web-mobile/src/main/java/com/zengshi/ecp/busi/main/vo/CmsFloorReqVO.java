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
public class CmsFloorReqVO extends EcpBasePageReqVO implements Serializable {
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
    
    private Long shopId;
    
    /**
     * 数据来源  行为分析  或者配置
     */
    private String dataSource;

    /**
     * 行为分析统计类型
     */
    private String countType;
    
    /**
     * 行为分析 统计对应商品分类
     */
    private String catgCode;
    
    private static final long serialVersionUID = 1L;

    
    public Long getTabId() {
        return tabId;
    }

    public void setTabId(Long tabId) {
        this.tabId = tabId;
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
    
    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

}
