package com.zengshi.ecp.busi.seller.goods.vo.category;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.server.front.dto.BaseInfo;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月29日下午5:18:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class CategoryVO extends BaseInfo{
	
	public static final String IGNORE_DATA_AUTH = "true";
	
	/**
	 * 不显示根节点.
	 */
	public static final int SHOW_ROOT_NO = 0;
	/**
	 * 显示根节点.
	 */
	public static final int SHOW_ROOT_YES = 1;
	
	
    
    private static final long serialVersionUID = -1868438298539009603L;

    private String catgCode;
    @NotBlank(message="{goods.CategoryVO.catgName.blank.error}")
    @Length(min=0,max=32,message="{goods.CategoryVO.catgName.length.error}")
    private String catgName;

    private Short catgLevel;
    @NotNull(message="{goods.CategoryVO.catgType.null.error}")
    private String catgType;

    private boolean allSubCategory;
    @Range(min=1, max=99999,message="{goods.CategoryVO.sortNo.range.error}")
    private Integer sortNo;

    private String catgParent;

    private String ifEntityCode;
    
    private String catgUrl;

    private Long catgTypeUnit;

    private Long minPrice;

    private Long maxPrice;

    @NotNull(message="{goods.MediaCategoryVO.shopId.null.error}")
    private Long  shopId;

    private String mediaUuid;
    
    private String mediaURL;

    private String status;

    private String ifShow;
    
    private String ifLeaf;
    
    private String ignoreDataAuth;
    
    private List<Long> shopIds;
    
    
    /**
     * 是否只显示树的根节点
     */
    private String maxShowNode;
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Timestamp createTime;

    private Long createStaff;
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Timestamp updateTime;

    private Long updateStaff;

    private List<Long> propIds;
    
    private List<Long> groupIds;
    
    /**
     * 目录ID。
     */
    private Long catlogId;
    /**
     * 站点ID.
     */
    private Long siteId;
    
    // zTree请求参数。
    private String id;
    private String pId;
    
    // 是否显示根(分两种,显示根目录,显示店铺)针对平台分类 0-不显示根目录或者店铺 1-显示根目录或者店铺.
    private int showRoot = 0;
    
    

    public boolean isAllSubCategory() {
		return allSubCategory;
	}

	public void setAllSubCategory(boolean allSubCategory) {
		this.allSubCategory = allSubCategory;
	}

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


    public String getMediaURL() {
		return mediaURL;
	}

	public void setMediaURL(String mediaURL) {
		this.mediaURL = mediaURL;
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

	public List<Long> getPropIds() {
		return propIds;
	}

	public void setPropIds(List<Long> propIds) {
		this.propIds = propIds;
	}

	public List<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<Long> groupIds) {
		this.groupIds = groupIds;
	}

	public Long getCatlogId() {
		return catlogId;
	}

	public void setCatlogId(Long catlogId) {
		this.catlogId = catlogId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public int getShowRoot() {
		return showRoot;
	}

	public void setShowRoot(int showRoot) {
		this.showRoot = showRoot;
	}

    public String getMaxShowNode() {
        return maxShowNode;
    }

    public void setMaxShowNode(String maxShowNode) {
        this.maxShowNode = maxShowNode;
    }

	public String getIgnoreDataAuth() {
		return ignoreDataAuth;
	}

	public void setIgnoreDataAuth(String ignoreDataAuth) {
		this.ignoreDataAuth = ignoreDataAuth;
	}

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

	public List<Long> getShopIds() {
		return shopIds;
	}

	public void setShopIds(List<Long> shopIds) {
		this.shopIds = shopIds;
	}

	
	
	
    
}