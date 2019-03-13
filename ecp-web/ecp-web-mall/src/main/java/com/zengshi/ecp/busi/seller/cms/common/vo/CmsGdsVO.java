package com.zengshi.ecp.busi.seller.cms.common.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: 楼层商品信息-出参 <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年11月2日下午10:17:12  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsGdsVO extends BaseResponseDTO {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    /**
     * 商品编码
     */
    private Long id;
    /**
     * 商品名称
     */
    private String gdsName;
    /**
     * 店铺名称
     */
    private String ShopName;
    /**
     * 商品副标题
     */
    private String gdsSubHead;
    /**
     * 商品指导价
     */
    private Long guidePrice;
    /**
     * 商品描述
     */
    private String gdsDesc;
    /**
     * 商品类型
     */
    private String gdsTypeCode;
    /**
     * 商品类型名称
     */
    private String gdsTypeName;
    /**
     * 商品状态
     */
    private String gdsStatus;
    /**
     * 商品状态名称
     */
    private String gdsStatusName;
    /**
     * 商品路径
     */
    private String url;
    /**
     * 商品图片路径
     */
    private String imageUrl;
    /**
     * 排序
     */
    private Long sortNo;
    /**
     * 店铺编码
     */
    private Long  shopId;
    /**
     * 商品标签
     */
    private String gdsLabel;
    /**
     * 自动上架时间
     */
    private Timestamp putonTime;
    /**
     * 自动下架时间
     */
    private Timestamp putoffTime;
    /**
     * 发货日期（预留）
     */
    private Long postTime;
    /**
     * 是否赠送积分
     */
    private String ifSendscore;
    /**
     * 是否推荐
     */
    private String ifRecomm;
    /**
     * 是否新品
     */
    private String ifNew;
    /**
     * 商品状态名称
     */
    private String shipTemplateName;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 创建工号
     */
    private Long createStaff;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 更新工号
     */
    private Long updateStaff;
    /**
     * 作者
     */
    private String prop1001;
    /**
     * ISBN
     */
    private String prop1002;
    /**
     * 出版日期
     */
    private String prop1005;
    /**
     * 版次
     */
    private String prop1010;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGdsName() {
        return gdsName;
    }
    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }
    public String getShopName() {
        return ShopName;
    }
    public void setShopName(String shopName) {
        ShopName = shopName;
    }
    public String getGdsSubHead() {
        return gdsSubHead;
    }
    public void setGdsSubHead(String gdsSubHead) {
        this.gdsSubHead = gdsSubHead;
    }
    public Long getGuidePrice() {
        return guidePrice;
    }
    public void setGuidePrice(Long guidePrice) {
        this.guidePrice = guidePrice;
    }
    public String getGdsDesc() {
        return gdsDesc;
    }
    public void setGdsDesc(String gdsDesc) {
        this.gdsDesc = gdsDesc;
    }
    public String getGdsTypeCode() {
        return gdsTypeCode;
    }
    public void setGdsTypeCode(String gdsTypeCode) {
        this.gdsTypeCode = gdsTypeCode;
    }
    public String getGdsTypeName() {
        return gdsTypeName;
    }
    public void setGdsTypeName(String gdsTypeName) {
        this.gdsTypeName = gdsTypeName;
    }
    public String getGdsStatus() {
        return gdsStatus;
    }
    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }
    public String getGdsStatusName() {
        return gdsStatusName;
    }
    public void setGdsStatusName(String gdsStatusName) {
        this.gdsStatusName = gdsStatusName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Long getSortNo() {
        return sortNo;
    }
    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public String getGdsLabel() {
        return gdsLabel;
    }
    public void setGdsLabel(String gdsLabel) {
        this.gdsLabel = gdsLabel;
    }
    public Timestamp getPutonTime() {
        return putonTime;
    }
    public void setPutonTime(Timestamp putonTime) {
        this.putonTime = putonTime;
    }
    public Timestamp getPutoffTime() {
        return putoffTime;
    }
    public void setPutoffTime(Timestamp putoffTime) {
        this.putoffTime = putoffTime;
    }
    public Long getPostTime() {
        return postTime;
    }
    public void setPostTime(Long postTime) {
        this.postTime = postTime;
    }
    public String getIfSendscore() {
        return ifSendscore;
    }
    public void setIfSendscore(String ifSendscore) {
        this.ifSendscore = ifSendscore;
    }
    public String getIfRecomm() {
        return ifRecomm;
    }
    public void setIfRecomm(String ifRecomm) {
        this.ifRecomm = ifRecomm;
    }
    public String getIfNew() {
        return ifNew;
    }
    public void setIfNew(String ifNew) {
        this.ifNew = ifNew;
    }
    public String getShipTemplateName() {
        return shipTemplateName;
    }
    public void setShipTemplateName(String shipTemplateName) {
        this.shipTemplateName = shipTemplateName;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public Long getCreateStaff() {
        return createStaff;
    }
    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }
    public Timestamp getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    public Long getUpdateStaff() {
        return updateStaff;
    }
    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }
    public String getProp1001() {
        return prop1001;
    }
    public void setProp1001(String prop1001) {
        this.prop1001 = prop1001;
    }
    public String getProp1002() {
        return prop1002;
    }
    public void setProp1002(String prop1002) {
        this.prop1002 = prop1002;
    }
    public String getProp1005() {
        return prop1005;
    }
    public void setProp1005(String prop1005) {
        this.prop1005 = prop1005;
    }
    public String getProp1010() {
        return prop1010;
    }
    public void setProp1010(String prop1010) {
        this.prop1010 = prop1010;
    }

}

