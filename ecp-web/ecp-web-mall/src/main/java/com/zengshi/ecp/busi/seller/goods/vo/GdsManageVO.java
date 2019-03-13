package com.zengshi.ecp.busi.seller.goods.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 商品管理界面入参<br>
 * Date:2015年8月19日下午2:29:01 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version
 * @since JDK 1.6
 */
public class GdsManageVO extends EcpBasePageReqVO {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 5005154431700467013L;

    private Long gdsId;// 商品编码

    private String gdsName;// 商品名称

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;// 起始时间

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;// 结束时间

    private String operateId;// 用于记录每条商品记录的唯一标识符号
    
    private String priceSort;//价格排序

    private String status;// 商品状态：上架、下架、删除、待上架

    private String operateFlag;// 商品操作标识符 1:上架，0:下架，9:删除

    private Long shopId;// 店铺编码

    private String gdsTypeId;// 产品类型

    private String catgCode;// 产品分类编码

    private Long shipTemplateId;// 运费编码
    
    private String ifGdsScore;//是否是积分商品。1：是，，，0：否
    
    private String isbn;//isbn号
    
    @DateTimeFormat(pattern="yyyy-MM-dd")//上架begin时间
    private Date begUpdateTime;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")//上架end时间
    private Date endUpdateTime;

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
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

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperateFlag() {
        return operateFlag;
    }

    public void setOperateFlag(String operateFlag) {
        this.operateFlag = operateFlag;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getGdsTypeId() {
        return gdsTypeId;
    }

    public void setGdsTypeId(String gdsTypeId) {
        this.gdsTypeId = gdsTypeId;
    }

    public Long getShipTemplateId() {
        return shipTemplateId;
    }

    public void setShipTemplateId(Long shipTemplateId) {
        this.shipTemplateId = shipTemplateId;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public String getIfGdsScore() {
        return ifGdsScore;
    }

    public void setIfGdsScore(String ifGdsScore) {
        this.ifGdsScore = ifGdsScore;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

	public Date getBegUpdateTime() {
		return begUpdateTime;
	}

	public Date getEndUpdateTime() {
		return endUpdateTime;
	}

	public void setBegUpdateTime(Date begUpdateTime) {
		this.begUpdateTime = begUpdateTime;
	}

	public void setEndUpdateTime(Date endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}

    public String getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }
    
    
    
}
