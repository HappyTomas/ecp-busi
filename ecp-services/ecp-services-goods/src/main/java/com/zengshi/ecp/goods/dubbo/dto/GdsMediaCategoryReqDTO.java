package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.zengshi.ecp.server.front.dto.BaseInfo;
@SuppressWarnings("rawtypes")
public class GdsMediaCategoryReqDTO extends BaseInfo    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
    private static final long serialVersionUID = -1L;

	private String catgCode;

    private String catgName;

    private Short catgLevel;

    private Integer sortNo;

    private String catgParent;

    private String catgUrl;

    private Long  shopId;

    private String mediaId;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String ifLeaf;
    
    private List<Long> shopIdLst;


    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName == null ? null : catgName.trim();
    }

    public Short getCatgLevel() {
        return catgLevel;
    }

    public void setCatgLevel(Short catgLevel) {
        this.catgLevel = catgLevel;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getCatgParent() {
        return catgParent;
    }

    public void setCatgParent(String catgParent) {
        this.catgParent = catgParent == null ? null : catgParent.trim();
    }

    public String getCatgUrl() {
        return catgUrl;
    }

    public void setCatgUrl(String catgUrl) {
        this.catgUrl = catgUrl == null ? null : catgUrl.trim();
    }

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
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

    public String getIfLeaf() {
		return ifLeaf;
	}

	public void setIfLeaf(String ifLeaf) {
		this.ifLeaf = ifLeaf;
	}
	
	public List<Long> getShopIdLst() {
		return shopIdLst;
	}

	public void setShopIdLst(List<Long> shopIdLst) {
		this.shopIdLst = shopIdLst;
	}
	/**
	 * 
	 * addShopIdToList:添加店铺ID，如果已经添加过则不继续添加. <br/> 
	 * 
	 * @author liyong7
	 * @param shopId 
	 * @since JDK 1.6
	 */
	public void addShopIdToList(Long shopId){
		if(null != shopId){
			if(null == shopIdLst){
				shopIdLst =  new ArrayList<>();
			}
			if(!shopIdLst.contains(shopId)){
				shopIdLst.add(shopId);
			}
		}
	}

	@Override
    public String toString() {
		return ToStringBuilder.reflectionToString(this);
    }
}
