package com.zengshi.ecp.busi.prom.groupchk.vo;

import java.io.Serializable;
import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class PromChkResponseVO extends BaseResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;//主题编码

    private Long promId;

    private String statusGroup;// 主题促销状态

    private String chkDesc;// 审核描述

    private String promTheme;// 主题促销名称

    private Long shopId;// 店铺ID

    private String shopName;// 店铺名称

    private String status;// 审核状态
    
    private String statusName;// 审核状态

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;
    
    private Long promInfoId;//店铺主题编码
    
    private String promInfoTheme;// 主题促销名称
    
    private Date startTime;

    private Date endTime;

    private Date showStartTime;

    private Date showEndTime;
    
    private String promTypeCode;

    private String promTypeName;
    
    private String keyWord;//搜索关键字
    
    public Long getPromInfoId() {
		return promInfoId;
	}

	public void setPromInfoId(Long promInfoId) {
		this.promInfoId = promInfoId;
	}

	public String getPromInfoTheme() {
		return promInfoTheme;
	}

	public void setPromInfoTheme(String promInfoTheme) {
		this.promInfoTheme = promInfoTheme;
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

	public Date getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(Date showStartTime) {
		this.showStartTime = showStartTime;
	}

	public Date getShowEndTime() {
		return showEndTime;
	}

	public void setShowEndTime(Date showEndTime) {
		this.showEndTime = showEndTime;
	}

	public String getPromTypeCode() {
		return promTypeCode;
	}

	public void setPromTypeCode(String promTypeCode) {
		this.promTypeCode = promTypeCode;
	}

	public String getPromTypeName() {
		return promTypeName;
	}

	public void setPromTypeName(String promTypeName) {
		this.promTypeName = promTypeName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public String getStatusGroup() {
        return statusGroup;
    }

    public void setStatusGroup(String statusGroup) {
        this.statusGroup = statusGroup;
    }

    public String getChkDesc() {
        return chkDesc;
    }

    public void setChkDesc(String chkDesc) {
        this.chkDesc = chkDesc;
    }

    public String getPromTheme() {
        return promTheme;
    }

    public void setPromTheme(String promTheme) {
        this.promTheme = promTheme;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
 

}