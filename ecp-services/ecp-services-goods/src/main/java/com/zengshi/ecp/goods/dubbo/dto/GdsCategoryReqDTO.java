package com.zengshi.ecp.goods.dubbo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.zengshi.ecp.goods.dao.model.GdsCategoryViewCriteria;
import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月11日上午10:08:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class GdsCategoryReqDTO extends BaseInfo<GdsCategoryViewCriteria> {
    

    private static final long serialVersionUID = 1L;

    /**
     * 分类编码
     */
    private String catgCode;

    /**
     * 分类名称
     */
    private String catgName;

    /**
     * 分类级别
     */
    private Short catgLevel;

    /**
     * 分类类型 1为平台分类  2为店铺分类
     */
    private String catgType;

    /**
     * 排序
     */
    private Integer sortNo;

    /**
     * 父分类编码
     */
    private String catgParent;

    /**
     * 实体创号策略
     */
    private String ifEntityCode;

    /**
     * 分类url
     */
    private String catgUrl;

    /**
     * 分类默认单位
     */
    private Long catgTypeUnit;

    /**
     * 最小价格
     */
    private Long minPrice;

    /**
     * 最大价格
     */
    private Long maxPrice;

    /**
     * 店铺编码
     */
    private Long  shopId;

    /**
     * 媒体编码
     */
    private String mediaUuid;

    /**
     * 分类状态
     */
    private String status;

    /**
     * 是否首页展示
     */
    private String ifShow;
    /**
     * 是否只显示树的根节点
     */
    private String maxShowNode;
    
    /**
     * 是否叶子节点
     */
    private String ifLeaf;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 创建人
     */
    private Long createStaff;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 更新人
     */
    private Long updateStaff;

    /**
     * 是否包含子分类商品
     */
    private Boolean isContainSub;
    /**
     * 目录ID。
     */
    private Long catlogId;
    /**
     * 站点ID。
     */
    private Long siteId;
    /**
     * 是否允许编辑。
     */
    private String ifAbleEdit;
    
    /**
     * 目标层级,该参数用于根据当前分类ID获取到指定层级分类时需要设值.
     */
    private Short targetLevel; 
    /**
     * 店铺ID列表。
     */
    private List<Long> shopIdLst;
    /**
     * 目录ID列表.用于多目录围限制
     */
    private List<Long> catlogIds;
    /**
     * 父节点限定条件集。
     */
    private List<String> catgParents;
    /**
     * 排序条件.
     */
    private String orderBy;
    /**
     * 分类族谱
     */
    private String catgPath;
    
    /**
     * 客户提成比例
     */
    private BigDecimal commission;
    
    /**
     * 排除掉的分类码。
     */
    private List<String> excludeCatgCode;
    /**
     * 包含的分类编码。
     */
    private List<String> includeCatgCode;
    

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

    public String getCatgType() {
        return catgType;
    }

    public void setCatgType(String catgType) {
        this.catgType = catgType == null ? null : catgType.trim();
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
        this.catgParent = catgParent;
    }

    public String getIfEntityCode() {
        return ifEntityCode;
    }

    public void setIfEntityCode(String ifEntityCode) {
        this.ifEntityCode = ifEntityCode == null ? null : ifEntityCode.trim();
    }

    public String getCatgUrl() {
        return catgUrl;
    }

    public void setCatgUrl(String catgUrl) {
        this.catgUrl = catgUrl == null ? null : catgUrl.trim();
    }

    public Long getCatgTypeUnit() {
        return catgTypeUnit;
    }

    public void setCatgTypeUnit(Long catgTypeUnit) {
        this.catgTypeUnit = catgTypeUnit;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public String getMediaUuid() {
        return mediaUuid;
    }

    public void setMediaUuid(String mediaUuid) {
        this.mediaUuid = mediaUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow == null ? null : ifShow.trim();
    }
    

    public String getIfLeaf() {
        return ifLeaf;
    }

    public void setIfLeaf(String ifLeaf) {
        this.ifLeaf = ifLeaf;
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

    public Boolean getIsContainSub() {
		return isContainSub;
	}

	public void setIsContainSub(Boolean isContainSub) {
		this.isContainSub = isContainSub;
	}

	public Long getCatlogId() {
		return catlogId;
	}

	public void setCatlogId(Long catlogId) {
		this.catlogId = catlogId;
	}


	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

    public String getMaxShowNode() {
        return maxShowNode;
    }

    public void setMaxShowNode(String maxShowNode) {
        this.maxShowNode = maxShowNode;
    }

	public String getIfAbleEdit() {
		return ifAbleEdit;
	}

	public void setIfAbleEdit(String ifAbleEdit) {
		this.ifAbleEdit = ifAbleEdit == null ? null : ifAbleEdit.trim();
	}

	public Short getTargetLevel() {
		return targetLevel;
	}

	public void setTargetLevel(Short targetLevel) {
		this.targetLevel = targetLevel;
	}

	public List<Long> getShopIdLst() {
		return shopIdLst;
	}

	public void setShopIdLst(List<Long> shopIdLst) {
		this.shopIdLst = shopIdLst;
	}
	
	
	public List<String> getCatgParents() {
        return catgParents;
    }

    public void setCatgParents(List<String> catgParents) {
        this.catgParents = catgParents;
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
	
	
	

	public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<Long> getCatlogIds() {
		return catlogIds;
	}

	public void setCatlogIds(List<Long> catlogIds) {
		this.catlogIds = catlogIds;
	}
	
	public void addCatlogIdToList(Long catlogId){
		if(null != catlogId){
			if(null == catlogIds){
				shopIdLst =  new ArrayList<>();
			}
			if(!catlogIds.contains(catlogId)){
				catlogIds.add(catlogId);
			}
		}
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

    public String getCatgPath() {
        return catgPath;
    }

    public void setCatgPath(String catgPath) {
        this.catgPath = catgPath;
    }

    public List<String> getExcludeCatgCode() {
        return excludeCatgCode;
    }

    public void setExcludeCatgCode(List<String> excludeCatgCode) {
        this.excludeCatgCode = excludeCatgCode;
    }

    public List<String> getIncludeCatgCode() {
        return includeCatgCode;
    }

    public void setIncludeCatgCode(List<String> includeCatgCode) {
        this.includeCatgCode = includeCatgCode;
    }

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	
	

   
}
