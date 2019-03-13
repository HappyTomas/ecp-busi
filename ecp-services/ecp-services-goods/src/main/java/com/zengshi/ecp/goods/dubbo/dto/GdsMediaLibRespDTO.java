package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
public class GdsMediaLibRespDTO extends BaseResponseDTO    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
    private static final long serialVersionUID = -1L;

	private Long id;

    private String libName;

    private String libDesc;

    private Long shopId;

    private Long vNow;

    private Long vLimit;

    private Long aNow;

    private Long aLimit;

    private Long pNow;

    private Long pLimit;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName == null ? null : libName.trim();
    }

    public String getLibDesc() {
        return libDesc;
    }

    public void setLibDesc(String libDesc) {
        this.libDesc = libDesc == null ? null : libDesc.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getvNow() {
        return vNow;
    }

    public void setvNow(Long vNow) {
        this.vNow = vNow;
    }

    public Long getvLimit() {
        return vLimit;
    }

    public void setvLimit(Long vLimit) {
        this.vLimit = vLimit;
    }

    public Long getaNow() {
        return aNow;
    }

    public void setaNow(Long aNow) {
        this.aNow = aNow;
    }

    public Long getaLimit() {
        return aLimit;
    }

    public void setaLimit(Long aLimit) {
        this.aLimit = aLimit;
    }

    public Long getpNow() {
        return pNow;
    }

    public void setpNow(Long pNow) {
        this.pNow = pNow;
    }

    public Long getpLimit() {
        return pLimit;
    }

    public void setpLimit(Long pLimit) {
        this.pLimit = pLimit;
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
        sb.append(", libName=").append(libName);
        sb.append(", libDesc=").append(libDesc);
        sb.append(", shopId=").append(shopId);
        sb.append(", vNow=").append(vNow);
        sb.append(", vLimit=").append(vLimit);
        sb.append(", aNow=").append(aNow);
        sb.append(", aLimit=").append(aLimit);
        sb.append(", pNow=").append(pNow);
        sb.append(", pLimit=").append(pLimit);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
