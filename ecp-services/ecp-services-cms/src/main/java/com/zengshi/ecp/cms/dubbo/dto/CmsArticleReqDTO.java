package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CmsArticleReqDTO extends BaseInfo {
	
	/**
	 * 栏目编码 
	 */
	private List<Long> channelIds;

   /*--------------------------以下为model后添加的字段 start--------------------------*/
    
    private Timestamp startPubTime;
    
    private Timestamp endPubTime;
    
    private Timestamp startLostTime;
    
    private Timestamp endLostTime;
    
    private Timestamp thisTime;//当前时间
    
    /*--------------------------以下为model后添加的字段 end------------------------*/
    
    private Long id;

    private String articleTitle;

    private String articleRemark;

    private String source;

    private String istop;

    private String status;

    private Timestamp pubTime;

    private Timestamp lostTime;

    private String thumbnailName;

    private String thumbnail;

    private String staticId;

    private String vfsName;

    private String vfsId;

    private Long siteId;

    private Long channelId;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String homepageIsShow;

    private String authorName;

    private String qrCode;
    
    private Integer sortNo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleRemark() {
        return articleRemark;
    }

    public void setArticleRemark(String articleRemark) {
        this.articleRemark = articleRemark == null ? null : articleRemark.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop == null ? null : istop.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getPubTime() {
        return pubTime;
    }

    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    public Timestamp getLostTime() {
        return lostTime;
    }

    public void setLostTime(Timestamp lostTime) {
        this.lostTime = lostTime;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName == null ? null : thumbnailName.trim();
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    public String getStaticId() {
        return staticId;
    }

    public void setStaticId(String staticId) {
        this.staticId = staticId == null ? null : staticId.trim();
    }

    public String getVfsName() {
        return vfsName;
    }

    public void setVfsName(String vfsName) {
        this.vfsName = vfsName == null ? null : vfsName.trim();
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
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

    public String getHomepageIsShow() {
        return homepageIsShow;
    }

    public void setHomepageIsShow(String homepageIsShow) {
        this.homepageIsShow = homepageIsShow == null ? null : homepageIsShow.trim();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public List<Long> getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List<Long> channelIds) {
		this.channelIds = channelIds;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", articleRemark=").append(articleRemark);
        sb.append(", source=").append(source);
        sb.append(", istop=").append(istop);
        sb.append(", status=").append(status);
        sb.append(", pubTime=").append(pubTime);
        sb.append(", lostTime=").append(lostTime);
        sb.append(", thumbnailName=").append(thumbnailName);
        sb.append(", thumbnail=").append(thumbnail);
        sb.append(", staticId=").append(staticId);
        sb.append(", vfsName=").append(vfsName);
        sb.append(", vfsId=").append(vfsId);
        sb.append(", siteId=").append(siteId);
        sb.append(", channelId=").append(channelId);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", homepageIsShow=").append(homepageIsShow);
        sb.append(", authorName=").append(authorName);
        sb.append(", qrCode=").append(qrCode);
        sb.append("]");
        return sb.toString();
    }

	public Timestamp getStartPubTime() {
		return startPubTime;
	}

	public void setStartPubTime(Timestamp startPubTime) {
		this.startPubTime = startPubTime;
	}

	public Timestamp getEndPubTime() {
		return endPubTime;
	}

	public void setEndPubTime(Timestamp endPubTime) {
		this.endPubTime = endPubTime;
	}

	public Timestamp getStartLostTime() {
		return startLostTime;
	}

	public void setStartLostTime(Timestamp startLostTime) {
		this.startLostTime = startLostTime;
	}

	public Timestamp getEndLostTime() {
		return endLostTime;
	}

	public void setEndLostTime(Timestamp endLostTime) {
		this.endLostTime = endLostTime;
	}

	public Timestamp getThisTime() {
		return thisTime;
	}

	public void setThisTime(Timestamp thisTime) {
		this.thisTime = thisTime;
	}
	
	public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

}
