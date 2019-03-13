package com.zengshi.ecp.busi.pageConfig.pageColor.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class CmsPageColorVO extends EcpBasePageReqVO implements Serializable{
    private Long id;


    @NotNull(message="{cms.pageColor.colorName.null.error}")
    private String colorName;

    private String showPic;
    
    @NotNull(message="{cms.pageColor.colorStyle.null.error}")
    private String colorStyle;
    
    private String remark;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;


    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName == null ? null : colorName.trim();
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic == null ? null : showPic.trim();
    }

    public String getColorStyle() {
        return colorStyle;
    }

    public void setColorStyle(String colorStyle) {
        this.colorStyle = colorStyle == null ? null : colorStyle.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", colorName=").append(colorName);
        sb.append(", showPic=").append(showPic);
        sb.append(", colorStyle=").append(colorStyle);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}