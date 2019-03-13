package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CmsLayoutItemPubReqDTO extends BaseInfo {
    
    /*--------------------------以下为model后添加的字段 start----------------------*/
    /**
     * 状态SET，用于查询
     */
    private Set<String> statusSet = new HashSet<String>();
    /*--------------------------以下为model后添加的字段 end------------------------*/
    
    private Long id;

    private Long itemId;

    private Long layoutId;

    private Long pageId;

    private Long modularId;

    private String status;

    private String itemSize;

    private Integer itemNo;

    private Integer rowNo;

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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(Long layoutId) {
        this.layoutId = layoutId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getModularId() {
        return modularId;
    }

    public void setModularId(Long modularId) {
        this.modularId = modularId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize == null ? null : itemSize.trim();
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
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
        sb.append(", itemId=").append(itemId);
        sb.append(", layoutId=").append(layoutId);
        sb.append(", pageId=").append(pageId);
        sb.append(", modularId=").append(modularId);
        sb.append(", status=").append(status);
        sb.append(", itemSize=").append(itemSize);
        sb.append(", itemNo=").append(itemNo);
        sb.append(", rowNo=").append(rowNo);
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
    
}
