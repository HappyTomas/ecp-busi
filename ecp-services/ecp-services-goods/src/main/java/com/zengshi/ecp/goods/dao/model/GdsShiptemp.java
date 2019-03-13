package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsShiptemp implements Serializable {
    private Long id;

    private String shipTemplateName;

    private String shipTemplateType;

    private String catgCode;

    private Long shopId;

    private String ifFree;

    private String shipInstruction;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
        sb.append(", id=").append(id);
        sb.append(", shipTemplateName=").append(shipTemplateName);
        sb.append(", shipTemplateType=").append(shipTemplateType);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", shopId=").append(shopId);
        sb.append(", ifFree=").append(ifFree);
        sb.append(", shipInstruction=").append(shipInstruction);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
