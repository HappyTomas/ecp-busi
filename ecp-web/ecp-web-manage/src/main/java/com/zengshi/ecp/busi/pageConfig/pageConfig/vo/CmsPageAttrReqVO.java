package com.zengshi.ecp.busi.pageConfig.pageConfig.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class CmsPageAttrReqVO extends EcpBasePageReqVO implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3529623113026858421L;

    private Long id;

    @NotNull(message="{cms.pageInfo.pageId.null.error}")
    private Long pageId;

    private String backgroundColor;

    private String backgroundPic;

    private String showBackFlag;

    private String status;

    private String backgroupShowType;

    private String matchingColour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBackgroundPic() {
        return backgroundPic;
    }

    public void setBackgroundPic(String backgroundPic) {
        this.backgroundPic = backgroundPic;
    }

    public String getShowBackFlag() {
        return showBackFlag;
    }

    public void setShowBackFlag(String showBackFlag) {
        this.showBackFlag = showBackFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBackgroupShowType() {
        return backgroupShowType;
    }

    public void setBackgroupShowType(String backgroupShowType) {
        this.backgroupShowType = backgroupShowType;
    }

    public String getMatchingColour() {
        return matchingColour;
    }

    public void setMatchingColour(String matchingColour) {
        this.matchingColour = matchingColour;
    }

    @Override
    public String toString() {
        return "CmsPageInfoReqVO [id=" + id + ", pageId=" + pageId + ", backgroundColor="
                + backgroundColor + ", backgroundPic=" + backgroundPic + ", showBackFlag="
                + showBackFlag + ", status=" + status + ", backgroupShowType=" + backgroupShowType
                + ", matchingColour=" + matchingColour + "]";
    }

}