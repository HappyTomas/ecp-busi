package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;
@SuppressWarnings("rawtypes")
public class GdsMainSkuCombReqDTO extends BaseInfo    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
    private static final long serialVersionUID = -1L;

	private Long gdsId;

    private Long skuId;

    private Long sourceGdsId;

    private Long sourceSkuId;

    private Long sourceSkuCount;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;


    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSourceGdsId() {
        return sourceGdsId;
    }

    public void setSourceGdsId(Long sourceGdsId) {
        this.sourceGdsId = sourceGdsId;
    }

    public Long getSourceSkuId() {
        return sourceSkuId;
    }

    public void setSourceSkuId(Long sourceSkuId) {
        this.sourceSkuId = sourceSkuId;
    }

    public Long getSourceSkuCount() {
        return sourceSkuCount;
    }

    public void setSourceSkuCount(Long sourceSkuCount) {
        this.sourceSkuCount = sourceSkuCount;
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
        sb.append(", gdsId=").append(gdsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", sourceGdsId=").append(sourceGdsId);
        sb.append(", sourceSkuId=").append(sourceSkuId);
        sb.append(", sourceSkuCount=").append(sourceSkuCount);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
