package com.zengshi.ecp.im.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ImHotlineInfo implements Serializable {
    private Long id;

    private Long shopId;

    private String moduleType;

    private String hotlinePerson;

    private Long hotlinePhone;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private Short receptionCount;

    private String orderEdit;

    private String platSource;
    
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    public String getHotlinePerson() {
        return hotlinePerson;
    }

    public void setHotlinePerson(String hotlinePerson) {
        this.hotlinePerson = hotlinePerson == null ? null : hotlinePerson.trim();
    }

    public Long getHotlinePhone() {
        return hotlinePhone;
    }

    public void setHotlinePhone(Long hotlinePhone) {
        this.hotlinePhone = hotlinePhone;
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

    public Short getReceptionCount() {
        return receptionCount;
    }

    public void setReceptionCount(Short receptionCount) {
        this.receptionCount = receptionCount;
    }

    public String getOrderEdit() {
        return orderEdit;
    }

    public void setOrderEdit(String orderEdit) {
        this.orderEdit = orderEdit == null ? null : orderEdit.trim();
    }

    public String getPlatSource() {
		return platSource;
	}

	public void setPlatSource(String platSource) {
		this.platSource = platSource;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", moduleType=").append(moduleType);
        sb.append(", hotlinePerson=").append(hotlinePerson);
        sb.append(", hotlinePhone=").append(hotlinePhone);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", receptionCount=").append(receptionCount);
        sb.append(", orderEdit=").append(orderEdit);
        sb.append(", platSource=").append(platSource);
        sb.append("]");
        return sb.toString();
    }
}