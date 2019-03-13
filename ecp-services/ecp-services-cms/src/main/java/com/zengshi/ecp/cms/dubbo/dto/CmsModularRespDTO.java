package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsModularRespDTO extends BaseResponseDTO {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/
    /**
     * 状态翻译
     */
    private String statusZH;
    /**
     * 模块类型翻译
     */
    private String modularTypeZH;
    /**
     * 平台类型翻译
     */
    private String platformTypeZH;
    /**
     * 模块与组件关系
     */
    private CmsModularComponentRespDTO modularComponentRespDTO;
    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;

    private String modularName;

    private String modularType;

    private String platformType;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String showPic;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModularName() {
        return modularName;
    }

    public void setModularName(String modularName) {
        this.modularName = modularName == null ? null : modularName.trim();
    }

    public String getModularType() {
        return modularType;
    }

    public void setModularType(String modularType) {
        this.modularType = modularType == null ? null : modularType.trim();
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType == null ? null : platformType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic == null ? null : showPic.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modularName=").append(modularName);
        sb.append(", modularType=").append(modularType);
        sb.append(", platformType=").append(platformType);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", showPic=").append(showPic);
        sb.append("]");
        return sb.toString();
    }
    
    public String getStatusZH() {
        return statusZH;
    }

    public void setStatusZH(String statusZH) {
        this.statusZH = statusZH;
    }

    public String getModularTypeZH() {
        return modularTypeZH;
    }

    public void setModularTypeZH(String modularTypeZH) {
        this.modularTypeZH = modularTypeZH;
    }
    
    public String getPlatformTypeZH() {
        return platformTypeZH;
    }

    public void setPlatformTypeZH(String platformTypeZH) {
        this.platformTypeZH = platformTypeZH;
    }
    
    public CmsModularComponentRespDTO getModularComponentRespDTO() {
        return modularComponentRespDTO;
    }

    public void setModularComponentRespDTO(CmsModularComponentRespDTO modularComponentRespDTO) {
        this.modularComponentRespDTO = modularComponentRespDTO;
    }

}
