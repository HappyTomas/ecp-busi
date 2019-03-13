/** 
 * Project Name:ecp-web-manage Maven Webapp 
 * File Name:CmsInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.cms.vo 
 * Date:2015-8-14下午3:14:04 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.cms.info.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage Maven Webapp <br>
 * Description:页面信息VO <br>
 * Date:2015-8-14下午3:14:04  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wenyf
 * @version  
 * @since JDK 1.7
 */
public class CmsInfoVO extends EcpBasePageReqVO implements Serializable {
    
    /*---以下新增 字段 start ---*/
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
    /*---以下新增 字段 end ---*/
    
    private Long id;

    @NotNull(message="{cms.info.infoTitle.notnull.err}")
    @Length(max=60,message="{cms.info.infoTitle.length.err}")
    private String infoTitle;

    private String remark;

    private String status;

    private Integer sortNo;

    private String source;

    @NotNull(message="{cms.info.infoType.notnull.err}")
    private String infoType;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pubTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lostTime;

    @NotNull(message="{cms.info.staticId.notnull.err}")
    private String staticId;

    private String vfsName;

    private String vfsId;

    private Long createStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    private Long updateStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    private Long siteId;

    private Long templateId;

    private Long placeId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle == null ? null : infoTitle.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType == null ? null : infoType.trim();
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Date getLostTime() {
        return lostTime;
    }

    public void setLostTime(Date lostTime) {
        this.lostTime = lostTime;
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

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
    
    public String getEditorText() {
        return editorText;
    }

    public void setEditorText(String editorText) {
        this.editorText = editorText;
    }

    public Date getStartPubTime() {
        return startPubTime;
    }

    public void setStartPubTime(Date startPubTime) {
        this.startPubTime = startPubTime;
    }

    public Date getEndPubTime() {
        return endPubTime;
    }

    public void setEndPubTime(Date endPubTime) {
        this.endPubTime = endPubTime;
    }

    public Date getStartLostTime() {
        return startLostTime;
    }

    public void setStartLostTime(Date startLostTime) {
        this.startLostTime = startLostTime;
    }

    public Date getEndLostTime() {
        return endLostTime;
    }

    public void setEndLostTime(Date endLostTime) {
        this.endLostTime = endLostTime;
    }
    
}

