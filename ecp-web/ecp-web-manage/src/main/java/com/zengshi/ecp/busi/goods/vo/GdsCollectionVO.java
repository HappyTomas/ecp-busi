package com.zengshi.ecp.busi.goods.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsCollectionVO extends EcpBasePageReqVO{
	/**
	 * 
     * Title: ECP <br>
     * Project Name:ecp-web-manage <br>
     * Description: 买家我的收藏页面传过来的入参参数VO<br>
     * Date:2015年9月11日上午9:49:44  <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author zhanbh
     * @version  
     * @since JDK 1.6 
	 */
	private static final long serialVersionUID = 6609266226529006836L;
    
	private String operateId;
	   
	private Long id; //收藏ID

    private Long gdsId; //商品编码

    private Long skuId; //单品编码

    private Long shopId; //店铺ID

    private String gdsName; //商品名称

    private Long gdsPrice; //收藏时价格

    private Long staffId; //收藏人

    private Timestamp collectionTime;  //收藏时间

    private String remark;//备注

    private String status; //状态

    public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
	
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
		this.gdsName = gdsName;
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
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
