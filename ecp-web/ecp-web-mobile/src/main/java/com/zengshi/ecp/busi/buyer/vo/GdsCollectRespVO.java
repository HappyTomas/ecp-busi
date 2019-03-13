package com.zengshi.ecp.busi.buyer.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 商品收藏返回DTO<br>
 * Date:2015年10月27日下午8:15:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsCollectRespVO extends BaseResponseDTO    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
    private static final long serialVersionUID = -1L;

	/**
	 * 收藏主键
	 */
	private Long id;

	/**
	 * 商品编码
	 */
    private Long gdsId;

    /**
     * 单品编码
     */
    private Long skuId;

    /**
     * 店铺编码
     */
    private Long shopId;

    /**
     * 商品名称
     */
    private String gdsName;
    
    /**
     * 单品属性串
     */
	private String skuProps;

	/**
	 * 收藏时价格
	 */
    private Long gdsPrice;
    
    private String gdsPriceYun;
    /**
     * 指导价
     */
    private Long guidePrice;

    /**
     * 用户编码
     */
    private Long staffId;
    
    /**
     * 用户名
     */
    private String staffName;

    /**
     * 收藏时间
     */
    private Timestamp collectionTime;

    /**
     * 现价
     */
    private Long nowPrice;
    
    private String nowPriceYun;

    /**
     * 用户收藏统计数
     */
    private Long collectStaffCount;
    
    /**
     * 主图UUID
     */
    private String skuMainPic;
    
    /**
     * 商品链接
     */
    private String gdsUrl;
    
    /**
     * 库存描述
     */
    private String stockInfo;
    
    /**
     * 库存数量
     */
    private Long stock;
    
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     */
    private String status;
    
    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String gdsStatus;
    
    private Long reducePrice;
    
    /**
     * 商品类型 (用于判断虚拟商品不能重复加入购物车)
     * add by gxq
     */
    private Long gdsTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public Long getGdsPrice() {
        return gdsPrice;
    }

    public void setGdsPrice(Long gdsPrice) {
        this.gdsPrice = gdsPrice;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Timestamp getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Timestamp collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public Long getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(Long nowPrice) {
		this.nowPrice = nowPrice;
	}
	
	public Long getCollectStaffCount() {
        return collectStaffCount;
    }

    public void setCollectStaffCount(Long collectStaffCount) {
        this.collectStaffCount = collectStaffCount;
    }

    public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getSkuProps() {
		return skuProps;
	}

	public void setSkuProps(String skuProps) {
		this.skuProps = skuProps;
	}
	
	public String getSkuMainPic() {
        return skuMainPic;
    }

    public void setSkuMainPic(String skuMainPic) {
        this.skuMainPic = skuMainPic;
    }

    public String getStockInfo() {
		return stockInfo;
	}

	public void setStockInfo(String stockInfo) {
		this.stockInfo = stockInfo;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getGdsUrl() {
		return gdsUrl;
	}

	public void setGdsUrl(String gdsUrl) {
		this.gdsUrl = gdsUrl;
	}

	@Override
    public String toString() {
        return "GdsCollectRespDTO [id=" + id + ", gdsId=" + gdsId + ", skuId=" + skuId
                + ", shopId=" + shopId + ", gdsName=" + gdsName + ", gdsPrice=" + gdsPrice
                + ", staffId=" + staffId + ", collectionTime=" + collectionTime + ", remark="
                + remark + ", status=" + status + ", createTime=" + createTime + ", createStaff="
                + createStaff + ", updateTime=" + updateTime + ", updateStaff=" + updateStaff
                + ", nowPrice=" + nowPrice + ", collectStaffCount=" + collectStaffCount + "]";
    }

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }

    public Long getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(Long guidePrice) {
        this.guidePrice = guidePrice;
    }

    public Long getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(Long gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }

    public Long getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(Long reducePrice) {
        this.reducePrice = reducePrice;
    }

    public String getGdsPriceYun() {
        return gdsPriceYun;
    }

    public void setGdsPriceYun(String gdsPriceYun) {
        this.gdsPriceYun = gdsPriceYun;
    }

    public String getNowPriceYun() {
        return nowPriceYun;
    }

    public void setNowPriceYun(String nowPriceYun) {
        this.nowPriceYun = nowPriceYun;
    }

}
