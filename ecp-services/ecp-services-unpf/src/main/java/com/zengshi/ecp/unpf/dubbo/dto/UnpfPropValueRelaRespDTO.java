package com.zengshi.ecp.unpf.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class UnpfPropValueRelaRespDTO extends BaseResponseDTO{
    private Long id;

    private Long shopAuthId;

    private Long shopId;

    private String platType;

    private String outPropId;

    private String outVid;

    private String outVidName;

    private String propId;

    private String vid;

    private String vidName;

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

    public Long getShopAuthId() {
        return shopAuthId;
    }

    public void setShopAuthId(Long shopAuthId) {
        this.shopAuthId = shopAuthId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
    }

    public String getOutPropId() {
        return outPropId;
    }

    public void setOutPropId(String outPropId) {
        this.outPropId = outPropId == null ? null : outPropId.trim();
    }

    public String getOutVid() {
        return outVid;
    }

    public void setOutVid(String outVid) {
        this.outVid = outVid == null ? null : outVid.trim();
    }

    public String getOutVidName() {
        return outVidName;
    }

    public void setOutVidName(String outVidName) {
        this.outVidName = outVidName == null ? null : outVidName.trim();
    }

    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId == null ? null : propId.trim();
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid == null ? null : vid.trim();
    }

    public String getVidName() {
        return vidName;
    }

    public void setVidName(String vidName) {
        this.vidName = vidName == null ? null : vidName.trim();
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
        sb.append(", shopAuthId=").append(shopAuthId);
        sb.append(", shopId=").append(shopId);
        sb.append(", platType=").append(platType);
        sb.append(", outPropId=").append(outPropId);
        sb.append(", outVid=").append(outVid);
        sb.append(", outVidName=").append(outVidName);
        sb.append(", propId=").append(propId);
        sb.append(", vid=").append(vid);
        sb.append(", vidName=").append(vidName);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}

