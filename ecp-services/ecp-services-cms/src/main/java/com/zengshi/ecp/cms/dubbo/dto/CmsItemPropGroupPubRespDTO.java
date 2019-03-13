package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsItemPropGroupPubRespDTO extends BaseResponseDTO {
    
    /*--------------------------以下为model后添加的字段 start--------------------------*/
    /**
     * 布局项属性的属性值LIST
     */
    private List<CmsItemPropGroupValuePubRespDTO> itemPropGroupValuePubRespDTOList;
    /*--------------------------以下为model后添加的字段 end------------------------*/

    private Long id;

    private Long itemPropId;

    private Long pageId;

    private Long itemId;

    private Long propId;

    private String propName;

    private String propValueId;

    private String propValue;

    private String ifHaveto;

    private String propValueType;

    private Long controlPropId;

    private Integer propGroupId;

    private Integer sortNo;

    private String status;

    private String isAutobuild;

    private String remark;

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

    public Long getItemPropId() {
        return itemPropId;
    }

    public void setItemPropId(Long itemPropId) {
        this.itemPropId = itemPropId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName == null ? null : propName.trim();
    }

    public String getPropValueId() {
        return propValueId;
    }

    public void setPropValueId(String propValueId) {
        this.propValueId = propValueId == null ? null : propValueId.trim();
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue == null ? null : propValue.trim();
    }

    public String getIfHaveto() {
        return ifHaveto;
    }

    public void setIfHaveto(String ifHaveto) {
        this.ifHaveto = ifHaveto == null ? null : ifHaveto.trim();
    }

    public String getPropValueType() {
        return propValueType;
    }

    public void setPropValueType(String propValueType) {
        this.propValueType = propValueType == null ? null : propValueType.trim();
    }

    public Long getControlPropId() {
        return controlPropId;
    }

    public void setControlPropId(Long controlPropId) {
        this.controlPropId = controlPropId;
    }

    public Integer getPropGroupId() {
        return propGroupId;
    }

    public void setPropGroupId(Integer propGroupId) {
        this.propGroupId = propGroupId;
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

    public String getIsAutobuild() {
        return isAutobuild;
    }

    public void setIsAutobuild(String isAutobuild) {
        this.isAutobuild = isAutobuild == null ? null : isAutobuild.trim();
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
        sb.append(", itemPropId=").append(itemPropId);
        sb.append(", pageId=").append(pageId);
        sb.append(", itemId=").append(itemId);
        sb.append(", propId=").append(propId);
        sb.append(", propName=").append(propName);
        sb.append(", propValueId=").append(propValueId);
        sb.append(", propValue=").append(propValue);
        sb.append(", ifHaveto=").append(ifHaveto);
        sb.append(", propValueType=").append(propValueType);
        sb.append(", controlPropId=").append(controlPropId);
        sb.append(", propGroupId=").append(propGroupId);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", status=").append(status);
        sb.append(", isAutobuild=").append(isAutobuild);
        sb.append(", remark=").append(remark);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
    
    public List<CmsItemPropGroupValuePubRespDTO> getItemPropGroupValuePubRespDTOList() {
        return itemPropGroupValuePubRespDTOList;
    }

    public void setItemPropGroupValuePubRespDTOList(
            List<CmsItemPropGroupValuePubRespDTO> itemPropGroupValuePubRespDTOList) {
        this.itemPropGroupValuePubRespDTOList = itemPropGroupValuePubRespDTOList;
    }
    
}
