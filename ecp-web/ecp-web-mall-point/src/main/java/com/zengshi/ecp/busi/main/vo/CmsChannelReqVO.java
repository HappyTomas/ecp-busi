package com.zengshi.ecp.busi.main.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**页面组件请求服务VO
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月21日下午5:49:47 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
public class CmsChannelReqVO extends EcpBasePageReqVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4236636435300147523L;

	private Long id;

    private String channelName;

    private String channelType;

    private Long channelParent;

    private String channelLabel;

    private String channelUrl;

    private Long channelTemplate;

    private Long siteId;

    private Integer sortNo;

    private String status;

    private String isresiteinfo;

    private Long siteinfoId;

    private String isOutLink;

    private String platformType;

    private String channelIcon;

    //---------------------------//
    /**
     * 组件返回vm的业务路径
     */
    private String path;
    /**
     * 用于标亮栏目
     */
    private String navType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public Long getChannelParent() {
		return channelParent;
	}

	public void setChannelParent(Long channelParent) {
		this.channelParent = channelParent;
	}

	public String getChannelLabel() {
		return channelLabel;
	}

	public void setChannelLabel(String channelLabel) {
		this.channelLabel = channelLabel;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public Long getChannelTemplate() {
		return channelTemplate;
	}

	public void setChannelTemplate(Long channelTemplate) {
		this.channelTemplate = channelTemplate;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsresiteinfo() {
		return isresiteinfo;
	}

	public void setIsresiteinfo(String isresiteinfo) {
		this.isresiteinfo = isresiteinfo;
	}

	public Long getSiteinfoId() {
		return siteinfoId;
	}

	public void setSiteinfoId(Long siteinfoId) {
		this.siteinfoId = siteinfoId;
	}

	public String getIsOutLink() {
		return isOutLink;
	}

	public void setIsOutLink(String isOutLink) {
		this.isOutLink = isOutLink;
	}

	public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	public String getChannelIcon() {
		return channelIcon;
	}

	public void setChannelIcon(String channelIcon) {
		this.channelIcon = channelIcon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNavType() {
        return navType;
    }

    public void setNavType(String navType) {
        this.navType = navType;
    }

    @Override
	public String toString() {
		return "CmsChannelReqVO [id=" + id + ", channelName=" + channelName
				+ ", channelType=" + channelType + ", channelParent="
				+ channelParent + ", channelLabel=" + channelLabel
				+ ", channelUrl=" + channelUrl + ", channelTemplate="
				+ channelTemplate + ", siteId=" + siteId + ", sortNo=" + sortNo
				+ ", status=" + status + ", isresiteinfo=" + isresiteinfo
				+ ", siteinfoId=" + siteinfoId + ", isOutLink=" + isOutLink
				+ ", platformType=" + platformType + ", channelIcon="
				+ channelIcon + ", navType=" + navType + "]";
	}
    
    
}
