package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CmsPageTypeReqDTO extends BaseInfo{
    
    /*--------------------------以下为model后添加的字段 start----------------------*/
    /**
     * 布局类型LIST
     */
    private List<CmsLayoutTypeReqDTO> layoutTypeReqDTOList;
    /**
     * 状态SET，用于查询
     */
    private Set<String> statusSet = new HashSet<String>();
    
    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;

    private String pageTypeName;

    private String platformType;

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

    public String getPageTypeName() {
        return pageTypeName;
    }

    public void setPageTypeName(String pageTypeName) {
        this.pageTypeName = pageTypeName == null ? null : pageTypeName.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pageTypeName=").append(pageTypeName);
        sb.append(", platformType=").append(platformType);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
    
    public Set<String> getStatusSet() {
        return statusSet;
    }

    public void setStatusSet(Set<String> statusSet) {
        this.statusSet = statusSet;
    }
    
    public List<CmsLayoutTypeReqDTO> getLayoutTypeReqDTOList() {
        return layoutTypeReqDTOList;
    }

    public void setLayoutTypeReqDTOList(List<CmsLayoutTypeReqDTO> layoutTypeReqDTOList) {
        this.layoutTypeReqDTOList = layoutTypeReqDTOList;
    }
}
