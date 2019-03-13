package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsVerifyRespDTO extends BaseResponseDTO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6754130052821193661L;
    /**
     * 商品编码ID。为 了适应于前店管理列表的框架
     */
    private Long id;
    
    private Long shopId;

    private Long gdsId;

    private Long skuId;

    private String status;

    private String operateType;

    public String getOperateStaffCode() {
		return operateStaffCode;
	}

	public void setOperateStaffCode(String operateStaffCode) {
		this.operateStaffCode = operateStaffCode;
	}

	public String getVerifyStaffCode() {
		return verifyStaffCode;
	}

	public void setVerifyStaffCode(String verifyStaffCode) {
		this.verifyStaffCode = verifyStaffCode;
	}

	private String operateStaffCode;
    
    private String verifyStaffCode;
    
    private Long operateStaff;
    
    private Long verifyStaff;

    private Timestamp operateTime;

    private String verifyStatus;

    private String verifyOption;

    private Timestamp verifyTime;

  

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    /**
     * 商品名称
     */
    private String gdsName;
    /**
     * 平台主分类
     */
    private String catgCode;
    
    private String isbn;
    /**
     * 商品指导价
     */
    private Long guidePrice;
    /**
     * 出版日期
     */
    private String publictionDate;
    /**
     * 作者
     */
    private String author;
    /**
     * 分类名称
     */
    private String catgName;
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Long getOperateStaff() {
        return operateStaff;
    }

    public void setOperateStaff(Long operateStaff) {
        this.operateStaff = operateStaff;
    }

    public Timestamp getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Timestamp operateTime) {
        this.operateTime = operateTime;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyOption() {
        return verifyOption;
    }

    public void setVerifyOption(String verifyOption) {
        this.verifyOption = verifyOption;
    }

    public Timestamp getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Timestamp verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Long getVerifyStaff() {
        return verifyStaff;
    }

    public void setVerifyStaff(Long verifyStaff) {
        this.verifyStaff = verifyStaff;
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

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public Long getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(Long guidePrice) {
        this.guidePrice = guidePrice;
    }

    public String getPublictionDate() {
        return publictionDate;
    }

    public void setPublictionDate(String publictionDate) {
        this.publictionDate = publictionDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}

