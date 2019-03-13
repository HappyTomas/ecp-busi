package com.zengshi.ecp.busi.pageConfig.utils.vo;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.alibaba.fastjson.annotation.JSONField;

public class CmsLinkGdsCategoryVO extends BaseInfo {


    /**
     * 不显示根节点.
     */
    public static final int SHOW_ROOT_NO = 0;

    /**
     * 显示根节点.
     */
    public static final int SHOW_ROOT_YES = 1;

    private static final long serialVersionUID = -1868438298539009603L;

    private String catgId;

    @NotNull(message = "{cms.gdscategory.catgName.null.error}")
    @Length(min = 1, max = 20, message = "{cms.gdscategory.catgName.length.error}")
    private String catgName;

    private Short catgLevel;

    private Integer sortNo;

    private String catgParent;

    private String catgCode;

    private String ifLeaf;

    private String catgUrl;

    private String status;

    private String showCatgId;

    private String showCatgIdName;
    
    private String siteId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp createTime;

    private Long createStaff;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp updateTime;

    private Long updateStaff;

    // zTree请求参数。
    private String id;

    private String pId;

    // 是否显示根(分两种,显示根目录,显示店铺)针对平台分类 0-不显示根目录或者店铺 1-显示根目录或者店铺.
    private int showRoot = 0;

    private String catgCodeZH;

    private String catgParentZH;
    
    private String mediaUuid;
    
    private String mediaURL;

    public String getCatgId() {
        return catgId;
    }

    public void setCatgId(String catgId) {
        this.catgId = catgId == null ? null : catgId.trim();
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

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getIfLeaf() {
        return ifLeaf;
    }

    public void setIfLeaf(String ifLeaf) {
        this.ifLeaf = ifLeaf == null ? null : ifLeaf.trim();
    }

    public String getCatgUrl() {
        return catgUrl;
    }

    public void setCatgUrl(String catgUrl) {
        this.catgUrl = catgUrl == null ? null : catgUrl.trim();
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

    public String getCatgCodeZH() {
        return catgCodeZH;
    }

    public void setCatgCodeZH(String catgCodeZH) {
        this.catgCodeZH = catgCodeZH;
    }

    public String getCatgParentZH() {
        return catgParentZH;
    }

    public void setCatgParentZH(String catgParentZH) {
        this.catgParentZH = catgParentZH;
    }
    
    public String getMediaUuid() {
        return mediaUuid;
    }

    public void setMediaUuid(String mediaUuid) {
        this.mediaUuid = mediaUuid == null ? null : mediaUuid.trim();
    }

    public String getShowCatgId() {
        return showCatgId;
    }

    public void setShowCatgId(String showCatgId) {
        this.showCatgId = showCatgId;
    }

    public String getShowCatgIdName() {
        return showCatgIdName;
    }

    public void setShowCatgIdName(String showCatgIdName) {
        this.showCatgIdName = showCatgIdName;
    }
    
    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

    
}
