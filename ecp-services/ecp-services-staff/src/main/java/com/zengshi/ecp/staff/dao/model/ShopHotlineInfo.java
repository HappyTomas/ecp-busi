package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ShopHotlineInfo implements Serializable {
    private Long id;

    private Long shopId;

    private String username;

    private String moduleType;

    private String hotlinePerson;

    private Long hotlinePhone;

    private String hotlineWebxin;

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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

    public String getHotlineWebxin() {
        return hotlineWebxin;
    }

    public void setHotlineWebxin(String hotlineWebxin) {
        this.hotlineWebxin = hotlineWebxin == null ? null : hotlineWebxin.trim();
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
        sb.append(", shopId=").append(shopId);
        sb.append(", username=").append(username);
        sb.append(", moduleType=").append(moduleType);
        sb.append(", hotlinePerson=").append(hotlinePerson);
        sb.append(", hotlinePhone=").append(hotlinePhone);
        sb.append(", hotlineWebxin=").append(hotlineWebxin);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}