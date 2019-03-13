package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsShiptempShopIdx implements Serializable {
    private Long shopId;

    private Long tempId;

    private String shipTemplateName;

    private String shipTemplateType;

    private String ifFree;

    private String shipInstruction;

    private String catgCode;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public String getShipTemplateName() {
        return shipTemplateName;
    }

    public void setShipTemplateName(String shipTemplateName) {
        this.shipTemplateName = shipTemplateName == null ? null : shipTemplateName.trim();
    }

    public String getShipTemplateType() {
        return shipTemplateType;
    }

    public void setShipTemplateType(String shipTemplateType) {
        this.shipTemplateType = shipTemplateType == null ? null : shipTemplateType.trim();
    }

    public String getIfFree() {
        return ifFree;
    }

    public void setIfFree(String ifFree) {
        this.ifFree = ifFree == null ? null : ifFree.trim();
    }

    public String getShipInstruction() {
        return shipInstruction;
    }

    public void setShipInstruction(String shipInstruction) {
        this.shipInstruction = shipInstruction == null ? null : shipInstruction.trim();
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopId=").append(shopId);
        sb.append(", tempId=").append(tempId);
        sb.append(", shipTemplateName=").append(shipTemplateName);
        sb.append(", shipTemplateType=").append(shipTemplateType);
        sb.append(", ifFree=").append(ifFree);
        sb.append(", shipInstruction=").append(shipInstruction);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
