package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GdsPresale implements Serializable {
    private Long id;

    private Long gdsId;

    private Long shopId;

    private String advanceState;

    private Timestamp presailStartTime;

    private Timestamp presailEndTime;

    private String presailMode;

    private Long bargainMoney;

    private String status;

    private Timestamp preSendTime;

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

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getAdvanceState() {
        return advanceState;
    }

    public void setAdvanceState(String advanceState) {
        this.advanceState = advanceState == null ? null : advanceState.trim();
    }

    public Timestamp getPresailStartTime() {
        return presailStartTime;
    }

    public void setPresailStartTime(Timestamp presailStartTime) {
        this.presailStartTime = presailStartTime;
    }

    public Timestamp getPresailEndTime() {
        return presailEndTime;
    }

    public void setPresailEndTime(Timestamp presailEndTime) {
        this.presailEndTime = presailEndTime;
    }

    public String getPresailMode() {
        return presailMode;
    }

    public void setPresailMode(String presailMode) {
        this.presailMode = presailMode == null ? null : presailMode.trim();
    }

    public Long getBargainMoney() {
        return bargainMoney;
    }

    public void setBargainMoney(Long bargainMoney) {
        this.bargainMoney = bargainMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getPreSendTime() {
        return preSendTime;
    }

    public void setPreSendTime(Timestamp preSendTime) {
        this.preSendTime = preSendTime;
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
        sb.append(", gdsId=").append(gdsId);
        sb.append(", shopId=").append(shopId);
        sb.append(", advanceState=").append(advanceState);
        sb.append(", presailStartTime=").append(presailStartTime);
        sb.append(", presailEndTime=").append(presailEndTime);
        sb.append(", presailMode=").append(presailMode);
        sb.append(", bargainMoney=").append(bargainMoney);
        sb.append(", status=").append(status);
        sb.append(", preSendTime=").append(preSendTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
