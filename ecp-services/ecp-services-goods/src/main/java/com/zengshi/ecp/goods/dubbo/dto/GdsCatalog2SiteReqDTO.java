package com.zengshi.ecp.goods.dubbo.dto;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsCatalog2SiteReqDTO extends BaseInfo {
	
    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    /**
     * 有效目录ID.
     */
	private List<Long> validCatlogIds;
	/**
	 * 无效目录ID.
	 */
	private List<Long> invalidCatlogIds;
	/**
	 * 站点ID.
	 */
	private Long siteId;
	/**
	 * 站点状态
	 */
	private String status;
	/**
	 * 单个目录ID.
	 */
	private Long catlogId;
	/**
	 * 返回目录信息状态条件.
	 */
	private String catalogStatus;

	/**
	 * 多个目录ID.
	 */
	private List<Long> catlogIds;
	
	
    public List<Long> getValidCatlogIds() {
		return validCatlogIds;
	}

	public void setValidCatlogIds(List<Long> validCatlogIds) {
		this.validCatlogIds = validCatlogIds;
	}

	public List<Long> getInvalidCatlogIds() {
		return invalidCatlogIds;
	}

	public void setInvalidCatlogIds(List<Long> invalidCatlogIds) {
		this.invalidCatlogIds = invalidCatlogIds;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

    
	public void addValidCatlogId(Long catlogId){
		if(null != catlogId){
			if(null == validCatlogIds){
				validCatlogIds = new ArrayList<Long>();
			}
			validCatlogIds.add(catlogId);
		}
	}
	
	public void addInValidCatlogId(Long catlogId){
		if(null != catlogId){
			if(null == invalidCatlogIds){
				invalidCatlogIds = new ArrayList<Long>();
			}
			invalidCatlogIds.add(catlogId);
		}
	}

	public Long getCatlogId() {
		return catlogId;
	}

	public void setCatlogId(Long catlogId) {
		this.catlogId = catlogId;
	}

	public String getCatalogStatus() {
		return catalogStatus;
	}

	public void setCatalogStatus(String catalogStatus) {
		this.catalogStatus = catalogStatus;
	}

	public List<Long> getCatlogIds() {
		return catlogIds;
	}

	public void setCatlogIds(List<Long> catlogIds) {
		this.catlogIds = catlogIds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
