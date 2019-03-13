package com.zengshi.ecp.sys.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ReportItemRespDTO extends BaseResponseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8137769415250525650L;

	

    private Long id;

    private String itemCode;

    private String itemDesc;

    private String itemValue;

    private Long shopId;

    private String status;

    private Timestamp calDate;

    private Timestamp createTime;

    private Timestamp uodateTime;

    private Long createStaff;

    private Long updateStaff;

    private String itemSource;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCalDate() {
        return calDate;
    }

    public void setCalDate(Timestamp calDate) {
        this.calDate = calDate;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUodateTime() {
        return uodateTime;
    }

    public void setUodateTime(Timestamp uodateTime) {
        this.uodateTime = uodateTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getItemSource() {
        return itemSource;
    }

    public void setItemSource(String itemSource) {
        this.itemSource = itemSource == null ? null : itemSource.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemCode=").append(itemCode);
        sb.append(", itemDesc=").append(itemDesc);
        sb.append(", itemValue=").append(itemValue);
        sb.append(", shopId=").append(shopId);
        sb.append(", status=").append(status);
        sb.append(", calDate=").append(calDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", uodateTime=").append(uodateTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", itemSource=").append(itemSource);
        sb.append("]");
        return sb.toString();
    }

}
