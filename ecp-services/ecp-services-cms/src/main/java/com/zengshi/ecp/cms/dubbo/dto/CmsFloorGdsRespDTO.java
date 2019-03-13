package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: 作为请求参数类<br>
 * Date:2015年8月7日下午5:07:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsFloorGdsRespDTO extends BaseResponseDTO {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/
    /**
     * 内容位置名称
     */
    private String placeName;
    /**
     * 楼层页签名称
     */
    private String tabName;
    /**
     * 楼层名称
     */
    private String floorName;
    /**
     * 商品名称
     */
    private String gdsName;
    /**
     * 状态
     */
    private String statusZH;
    /**
     * 商品名称
     */
    private String gdsIdStrName;
    
    /**
     * 商品名称id
     */
    private String gdsIdStr;
    /**
     * 商品图片url
     */
    private String gdsImgUrl;
    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;

    private Long gdsId;

    private String linkName;

    private String linkUrl;

    private Integer sortNo;

    private String remark;

    private Long placeId;

    private Long tabId;

    private Long floorId;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String status;
    
    private String isProm;

    private Long promId;

    private static final long serialVersionUID = 1L;

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

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getTabId() {
        return tabId;
    }

    public void setTabId(Long tabId) {
        this.tabId = tabId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    public String getIsProm() {
        return isProm;
    }

    public void setIsProm(String isProm) {
        this.isProm = isProm == null ? null : isProm.trim();
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", linkName=").append(linkName);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", remark=").append(remark);
        sb.append(", placeId=").append(placeId);
        sb.append(", tabId=").append(tabId);
        sb.append(", floorId=").append(floorId);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", isProm=").append(isProm);
        sb.append(", promId=").append(promId);
        sb.append("]");
        return sb.toString();
    }
    
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName == null ? null : tabName.trim();
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName == null ? null : floorName.trim();
    }
    
    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }
    
    public String getStatusZH() {
        return statusZH;
    }

    public void setStatusZH(String statusZH) {
        this.statusZH = statusZH;
    }

	public String getGdsIdStrName() {
		return gdsIdStrName;
	}

	public void setGdsIdStrName(String gdsIdStrName) {
		this.gdsIdStrName = gdsIdStrName;
	}

	public String getGdsIdStr() {
		return gdsIdStr;
	}

	public void setGdsIdStr(String gdsIdStr) {
		this.gdsIdStr = gdsIdStr;
	}

	public String getGdsImgUrl() {
		return gdsImgUrl;
	}

	public void setGdsImgUrl(String gdsImgUrl) {
		this.gdsImgUrl = gdsImgUrl;
	}
	
}

