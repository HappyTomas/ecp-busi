package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsVerifyReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6848024382000488499L;
    
    private Long shopId;

    private Long verifyId;

    private Long gdsId;

    private Long skuId;

    private String status;

    private String operateType;

    private Long operateStaff;

    private Timestamp operateTime;

    private String verifyStatus;

    private String verifyOption;

	private Long gdsTypeId;


    private Timestamp verifyTime;

    private Long verifyStaff;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String gdsName;

    private String catgCode;

    private String isbn;
    
    /**
     * 创建时间开始
     */
    private Timestamp begCreateTime;

    /**
     * 创建时间结束
     */
    private Timestamp endCreateTime;
    /**
     * 店铺的企业编码。删除审核需要用到
     */
    private Long companyId;
    /**
     * 用于进行批量审核操作的商品编码数组
     */
    private Long[] ids;
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Long verifyId) {
        this.verifyId = verifyId;
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

    public Timestamp getBegCreateTime() {
        return begCreateTime;
    }

    public void setBegCreateTime(Timestamp begCreateTime) {
        this.begCreateTime = begCreateTime;
    }

    public Timestamp getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Timestamp endCreateTime) {
        this.endCreateTime = endCreateTime;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
    
    public Long getGdsTypeId() {
		return gdsTypeId;
	}

	public void setGdsTypeId(Long gdsTypeId) {
		this.gdsTypeId = gdsTypeId;
	}
}

