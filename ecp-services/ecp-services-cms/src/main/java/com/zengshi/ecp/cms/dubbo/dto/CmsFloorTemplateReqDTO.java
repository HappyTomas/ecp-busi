package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CmsFloorTemplateReqDTO extends BaseInfo{
    
    /*--------------------------以下为model后添加的字段 start----------------------*/
    /**
     * 楼层内容位置
     */
    private List<CmsFloorPlaceReqDTO> floorPlaceReqDTOList;
    /*--------------------------以下为model后添加的字段 end------------------------*/
    
    private Long id;

    private String templateName;

    private String vfsId;

    private String status;

    private Integer sortNo;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private Integer floorPlaceNum;

    private Short templateNo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
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

    public Integer getFloorPlaceNum() {
        return floorPlaceNum;
    }

    public void setFloorPlaceNum(Integer floorPlaceNum) {
        this.floorPlaceNum = floorPlaceNum;
    }

    public Short getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(Short templateNo) {
        this.templateNo = templateNo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateName=").append(templateName);
        sb.append(", vfsId=").append(vfsId);
        sb.append(", status=").append(status);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", floorPlaceNum=").append(floorPlaceNum);
        sb.append(", templateNo=").append(templateNo);
        sb.append("]");
        return sb.toString();
    }
    
    public List<CmsFloorPlaceReqDTO> getFloorPlaceReqDTOList() {
        return floorPlaceReqDTOList;
    }

    public void setFloorPlaceReqDTOList(List<CmsFloorPlaceReqDTO> floorPlaceReqDTOList) {
        this.floorPlaceReqDTOList = floorPlaceReqDTOList;
    }
}
