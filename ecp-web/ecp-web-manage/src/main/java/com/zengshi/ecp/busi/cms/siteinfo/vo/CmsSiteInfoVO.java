package com.zengshi.ecp.busi.cms.siteinfo.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class CmsSiteInfoVO  extends EcpBasePageReqVO implements Serializable{

    /*--------------------------以下为model后添加的字段 start--------------------------*/
    
    /**
     * 信息内容
     */
    private String editorText;
    /**
     * 栏目名称
     */
    private String channelZH;
    
    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;

    @NotNull(message="{cms.siteInfo.siteInfoTitle.notnull.err}")
    @Length(max=32,message="{cms.siteInfo.siteInfoTitle.length.err}")
    private String siteInfoName;

    @NotNull(message="{cms.siteInfo.siteId.notnull.err}")
    private Long siteId;

    private String siteInfoType;

    private String siteInfoUrl;

    private Integer sortNo;

    private String status;

    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp createTime;

    private String createStaff;

    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp updateTime;

    private String updateStaff;

    private String staticId;
    
    private String channelId;
    @NotNull(message="{cms.siteInfo.parent.notnull.err}")
    private Long parent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteInfoName() {
        return siteInfoName;
    }

    public void setSiteInfoName(String siteInfoName) {
        this.siteInfoName = siteInfoName == null ? null : siteInfoName.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteInfoType() {
        return siteInfoType;
    }

    public void setSiteInfoType(String siteInfoType) {
        this.siteInfoType = siteInfoType == null ? null : siteInfoType.trim();
    }

    public String getSiteInfoUrl() {
        return siteInfoUrl;
    }

    public void setSiteInfoUrl(String siteInfoUrl) {
        this.siteInfoUrl = siteInfoUrl == null ? null : siteInfoUrl.trim();
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
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff == null ? null : createStaff.trim();
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff == null ? null : updateStaff.trim();
    }

    public String getStaticId() {
        return staticId;
    }

    public void setStaticId(String staticId) {
        this.staticId = staticId == null ? null : staticId.trim();
    }
    
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", siteInfoName=").append(siteInfoName);
        sb.append(", siteId=").append(siteId);
        sb.append(", siteInfoType=").append(siteInfoType);
        sb.append(", siteInfoUrl=").append(siteInfoUrl);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", staticId=").append(staticId);
        sb.append(", channelId=").append(channelId);
        sb.append(", parent=").append(parent);
        sb.append("]");
        return sb.toString();
    }

    public String getEditorText() {
        return editorText;
    }

    public void setEditorText(String editorText) {
        this.editorText = editorText;
    }
	
    public String getChannelZH() {
        return channelZH;
    }

    public void setChannelZH(String channelZH) {
        this.channelZH = channelZH;
    }
}
