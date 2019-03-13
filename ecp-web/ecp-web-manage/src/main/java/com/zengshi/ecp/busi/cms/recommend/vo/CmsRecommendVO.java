package com.zengshi.ecp.busi.cms.recommend.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月21日下午5:49:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version  
 * @since JDK 1.6 
 */  
public class CmsRecommendVO extends EcpBasePageReqVO implements Serializable{

    private Long id;

    @NotNull(message="{cms.recommend.recommendType.null.error}")
    private String recommendType;

    @NotNull(message="{cms.recommend.recommendGdsId.null.error}")
    private Long recommendGdsId;

    @NotNull(message="{cms.recommend.authorName.null.error}")
    @Length(max=20, message="{cms.recommend.authorName.length.error}")
    private String authorName;

    @NotNull(message="{cms.recommend.authorIntroduction.null.error}")
    @Length(max=150, message="{cms.recommend.authorIntroduction.length.error}")
    private String authorIntroduction;

    @NotNull(message="{cms.recommend.authorImage.null.error}")
    private String authorImage;

    private String otherProduction;

    private String recommendProduction;

    private String otherLike;

    private String status;

    private Integer sortNo;

    private Long createStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp createTime;

    private Long updateStaff;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType == null ? null : recommendType.trim();
    }

    public Long getRecommendGdsId() {
        return recommendGdsId;
    }

    public void setRecommendGdsId(Long recommendGdsId) {
        this.recommendGdsId = recommendGdsId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public String getAuthorIntroduction() {
        return authorIntroduction;
    }

    public void setAuthorIntroduction(String authorIntroduction) {
        this.authorIntroduction = authorIntroduction == null ? null : authorIntroduction.trim();
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage == null ? null : authorImage.trim();
    }

    public String getOtherProduction() {
        return otherProduction;
    }

    public void setOtherProduction(String otherProduction) {
        this.otherProduction = otherProduction == null ? null : otherProduction.trim();
    }

    public String getRecommendProduction() {
        return recommendProduction;
    }

    public void setRecommendProduction(String recommendProduction) {
        this.recommendProduction = recommendProduction == null ? null : recommendProduction.trim();
    }

    public String getOtherLike() {
        return otherLike;
    }

    public void setOtherLike(String otherLike) {
        this.otherLike = otherLike == null ? null : otherLike.trim();
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

}

