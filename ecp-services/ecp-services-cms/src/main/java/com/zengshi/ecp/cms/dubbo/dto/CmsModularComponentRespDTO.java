package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsModularComponentRespDTO extends BaseResponseDTO {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/
    /**
     * 状态翻译
     */
    private String statusZH;
    /**
     * 状态翻译
     */
    private String applyPageTypeZH;
    /**
     * 组件对象
     */
    private CmsComponentRespDTO componentRespDTO;
    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;

    private Long modularId;

    private String applyPageType;

    private String applyItemSize;

    private String modularClass;

    private Long componentId;

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

    public Long getModularId() {
        return modularId;
    }

    public void setModularId(Long modularId) {
        this.modularId = modularId;
    }

    public String getApplyPageType() {
        return applyPageType;
    }

    public void setApplyPageType(String applyPageType) {
        this.applyPageType = applyPageType == null ? null : applyPageType.trim();
    }

    public String getApplyItemSize() {
        return applyItemSize;
    }

    public void setApplyItemSize(String applyItemSize) {
        this.applyItemSize = applyItemSize == null ? null : applyItemSize.trim();
    }

    public String getModularClass() {
        return modularClass;
    }

    public void setModularClass(String modularClass) {
        this.modularClass = modularClass == null ? null : modularClass.trim();
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modularId=").append(modularId);
        sb.append(", applyPageType=").append(applyPageType);
        sb.append(", applyItemSize=").append(applyItemSize);
        sb.append(", modularClass=").append(modularClass);
        sb.append(", componentId=").append(componentId);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
    
    public String getStatusZH() {
        return statusZH;
    }

    public void setStatusZH(String statusZH) {
        this.statusZH = statusZH;
    }

    public CmsComponentRespDTO getComponentRespDTO() {
        return componentRespDTO;
    }

    public void setComponentRespDTO(CmsComponentRespDTO componentRespDTO) {
        this.componentRespDTO = componentRespDTO;
    }

	public String getApplyPageTypeZH() {
		return applyPageTypeZH;
	}

	public void setApplyPageTypeZH(String applyPageTypeZH) {
		this.applyPageTypeZH = applyPageTypeZH;
	}
    
}
