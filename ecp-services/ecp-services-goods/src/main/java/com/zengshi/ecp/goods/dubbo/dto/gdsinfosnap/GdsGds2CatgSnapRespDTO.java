package com.zengshi.ecp.goods.dubbo.dto.gdsinfosnap;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
public class GdsGds2CatgSnapRespDTO extends BaseResponseDTO    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -7878776490650826184L;

	private Long snapId;

    private Long gdsId;

    private String gdsName;

    private String catgCode;

    private String categoryRange;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;


    public Long getSnapId() {
        return snapId;
    }

    public void setSnapId(Long snapId) {
        this.snapId = snapId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getCategoryRange() {
        return categoryRange;
    }

    public void setCategoryRange(String categoryRange) {
        this.categoryRange = categoryRange == null ? null : categoryRange.trim();
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
        sb.append(", snapId=").append(snapId);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", gdsName=").append(gdsName);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", categoryRange=").append(categoryRange);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
