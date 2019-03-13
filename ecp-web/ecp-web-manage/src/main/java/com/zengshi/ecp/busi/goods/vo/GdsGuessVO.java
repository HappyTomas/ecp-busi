package com.zengshi.ecp.busi.goods.vo;

import org.hibernate.validator.constraints.Range;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsGuessVO extends  EcpBasePageReqVO{

    private static final long serialVersionUID = -6620744971867241470L;
    


    private String catgCode;

    private String skuName;
    
    private Long skuId;
    
    private String operateId;
    
    @Range(min=1, max=99999,message="{goods.CategoryVO.sortNo.range.error}")

    private Integer sortNo;
    
    private String ifDefault;
    
    private Long gdsId;
    
    private String relatedName;
    
    private String relatedCatgCode;
    
    private String gdsName;

	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getIfDefault() {
		return ifDefault;
	}

	public void setIfDefault(String ifDefault) {
		this.ifDefault = ifDefault;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	public String getRelatedName() {
		return relatedName;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}

	public String getRelatedCatgCode() {
		return relatedCatgCode;
	}

	public void setRelatedCatgCode(String relatedCatgCode) {
		this.relatedCatgCode = relatedCatgCode;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

    
    
}
