package com.zengshi.ecp.staff.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ShopHotlineReqDTO extends BaseInfo {
    private Long id;

    private Long shopId;

    private String moduleType;

    private String hotlineNo;

    private String hotlinePerson;

    private Long hotlinePhone;

    private String hotlineQq;

    private String qqType;

    private String hotlineWebxin;

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

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    public String getHotlineNo() {
        return hotlineNo;
    }

    public void setHotlineNo(String hotlineNo) {
        this.hotlineNo = hotlineNo == null ? null : hotlineNo.trim();
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

    public String getHotlineQq() {
        return hotlineQq;
    }

    public void setHotlineQq(String hotlineQq) {
        this.hotlineQq = hotlineQq == null ? null : hotlineQq.trim();
    }

    public String getQqType() {
        return qqType;
    }

    public void setQqType(String qqType) {
        this.qqType = qqType == null ? null : qqType.trim();
    }

    public String getHotlineWebxin() {
        return hotlineWebxin;
    }

    public void setHotlineWebxin(String hotlineWebxin) {
        this.hotlineWebxin = hotlineWebxin == null ? null : hotlineWebxin.trim();
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
        sb.append(", moduleType=").append(moduleType);
        sb.append(", hotlineNo=").append(hotlineNo);
        sb.append(", hotlinePerson=").append(hotlinePerson);
        sb.append(", hotlinePhone=").append(hotlinePhone);
        sb.append(", hotlineQq=").append(hotlineQq);
        sb.append(", qqType=").append(qqType);
        sb.append(", hotlineWebxin=").append(hotlineWebxin);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}