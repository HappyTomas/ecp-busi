package com.zengshi.ecp.cms.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CmsLayoutPreRespDTO extends BaseResponseDTO {
    
    /*----以下是model新添加字段 start---*/
    /**
     * 状态翻译
     */
    private String statusZH;
    /**
     * 布局类型对象
     */
    private CmsLayoutTypeRespDTO cmsLayoutTypeRespDTO;
    /**
     * 页面布局项预览LIST（促销）
     */
    private List<CmsLayoutItemPreRespDTO> layoutItemPreRespDTOList;
    /**
     * 页面布局项预览Map（后期统一用此属性）
     */
    private Map<Integer,List<CmsLayoutItemPreRespDTO>> layoutItemPreRespDTOMap;

    /*----以下是model新添加字段  end ---*/
    private Long id;

    private Long pageId;

    private Long layoutTypeId;

    private String status;

    private Integer showOrder;

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

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getLayoutTypeId() {
        return layoutTypeId;
    }

    public void setLayoutTypeId(Long layoutTypeId) {
        this.layoutTypeId = layoutTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
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
        sb.append(", pageId=").append(pageId);
        sb.append(", layoutTypeId=").append(layoutTypeId);
        sb.append(", status=").append(status);
        sb.append(", showOrder=").append(showOrder);
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
    
    public CmsLayoutTypeRespDTO getCmsLayoutTypeRespDTO() {
        return cmsLayoutTypeRespDTO;
    }

    public void setCmsLayoutTypeRespDTO(CmsLayoutTypeRespDTO cmsLayoutTypeRespDTO) {
        this.cmsLayoutTypeRespDTO = cmsLayoutTypeRespDTO;
    }

    public List<CmsLayoutItemPreRespDTO> getLayoutItemPreRespDTOList() {
        return layoutItemPreRespDTOList;
    }

    public void setLayoutItemPreRespDTOList(List<CmsLayoutItemPreRespDTO> layoutItemPreRespDTOList) {
        this.layoutItemPreRespDTOList = layoutItemPreRespDTOList;
    }
    
    public Map<Integer, List<CmsLayoutItemPreRespDTO>> getLayoutItemPreRespDTOMap() {
        return layoutItemPreRespDTOMap;
    }

    public void setLayoutItemPreRespDTOMap(
            Map<Integer, List<CmsLayoutItemPreRespDTO>> layoutItemPreRespDTOMap) {
        this.layoutItemPreRespDTOMap = layoutItemPreRespDTOMap;
    }
    
}
