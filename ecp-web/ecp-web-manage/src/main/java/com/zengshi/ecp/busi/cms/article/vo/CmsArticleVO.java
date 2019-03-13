package com.zengshi.ecp.busi.cms.article.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class CmsArticleVO extends EcpBasePageReqVO implements Serializable{

/*--------------------------以下为model后添加的字段 start--------------------------*/
	 /**
     * 信息内容
     */
    private String editorText;
    
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startPubTime;
    
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endPubTime;
    
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startLostTime;
    
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endLostTime;
    
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date thisTime;//当前时间
    /**
     * 状态翻译
     */
    private String statusZH;
    /**
     * 栏目名称
     */
    private String channelZH;
    /**
     * 站点名称
     */
    private String siteZH;
    /**
     * 来源翻译
     */
    private String sourceZH;
    
    /*--------------------------以下为model后添加的字段 end------------------------*/
	private Long id;

	@NotNull(message="{cms.article.articleTitle.null.error}")
    @Length(max=128, message="{cms.article.articleTitle.length.error}")
	private String articleTitle;

	private String articleRemark;

	private String source;

	@NotNull(message="{cms.article.istop.null.error}")
	private String istop;

	private String status;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date pubTime;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lostTime;

	private String thumbnailName;

	private String thumbnail;

	private String staticId;

	private String vfsName;

	private String vfsId;

	@NotNull(message="{cms.article.siteId.null.error}")
	private Long siteId;

	private Long channelId;

	private Long createStaff;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp createTime;

	private Long updateStaff;

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp updateTime;
	
	@NotNull(message="{cms.article.homepageIsShow.null.error}")
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
		this.articleRemark = articleRemark == null ? null : articleRemark
				.trim();
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

	
	public Date getPubTime() {
		return pubTime;
	}

	public Date getLostTime() {
		return lostTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public void setLostTime(Date lostTime) {
		this.lostTime = lostTime;
	}

	public String getThumbnailName() {
		return thumbnailName;
	}

	public void setThumbnailName(String thumbnailName) {
		this.thumbnailName = thumbnailName == null ? null : thumbnailName
				.trim();
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

	public Date getStartPubTime() {
		return startPubTime;
	}

	public Date getEndPubTime() {
		return endPubTime;
	}

	public Date getStartLostTime() {
		return startLostTime;
	}

	public Date getEndLostTime() {
		return endLostTime;
	}

	public Date getThisTime() {
		return thisTime;
	}

	public void setStartPubTime(Date startPubTime) {
		this.startPubTime = startPubTime;
	}

	public void setEndPubTime(Date endPubTime) {
		this.endPubTime = endPubTime;
	}

	public void setStartLostTime(Date startLostTime) {
		this.startLostTime = startLostTime;
	}

	public void setEndLostTime(Date endLostTime) {
		this.endLostTime = endLostTime;
	}

	public void setThisTime(Date thisTime) {
		this.thisTime = thisTime;
	}

	public String getStatusZH() {
		return statusZH;
	}

	public void setStatusZH(String statusZH) {
		this.statusZH = statusZH;
	}

	public String getChannelZH() {
		return channelZH;
	}

	public void setChannelZH(String channelZH) {
		this.channelZH = channelZH;
	}

	public String getSiteZH() {
		return siteZH;
	}

	public void setSiteZH(String siteZH) {
		this.siteZH = siteZH;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getSourceZH() {
		return sourceZH;
	}

	public void setSourceZH(String sourceZH) {
		this.sourceZH = sourceZH;
	}

	public String getEditorText() {
		return editorText;
	}

	public void setEditorText(String editorText) {
		this.editorText = editorText;
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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

	
}
