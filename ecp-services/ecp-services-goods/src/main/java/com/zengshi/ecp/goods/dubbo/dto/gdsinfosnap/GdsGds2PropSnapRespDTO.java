package com.zengshi.ecp.goods.dubbo.dto.gdsinfosnap;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
public class GdsGds2PropSnapRespDTO extends BaseResponseDTO    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -1505468469472405745L;

	private Long snapId;

    private Long gdsId;

    private Long  shopId;

    private Long propId;

    private String propName;

    private Long propValueId;

    private String propValue;

    private String propType;

    private String propValueType;

    private String propRtype;

    private String ifMust;

    private String ifCheck;

    private String ifBastic;

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

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName == null ? null : propName.trim();
    }

    public Long getPropValueId() {
        return propValueId;
    }

    public void setPropValueId(Long propValueId) {
        this.propValueId = propValueId;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue == null ? null : propValue.trim();
    }

    public String getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType;
    }

    public String getPropValueType() {
        return propValueType;
    }

    public void setPropValueType(String propValueType) {
        this.propValueType = propValueType;
    }

    public String getPropRtype() {
        return propRtype;
    }

    public void setPropRtype(String propRtype) {
        this.propRtype = propRtype == null ? null : propRtype.trim();
    }

    public String getIfMust() {
        return ifMust;
    }

    public void setIfMust(String ifMust) {
        this.ifMust = ifMust == null ? null : ifMust.trim();
    }

    public String getIfCheck() {
        return ifCheck;
    }

    public void setIfCheck(String ifCheck) {
        this.ifCheck = ifCheck == null ? null : ifCheck.trim();
    }

    public String getIfBastic() {
        return ifBastic;
    }

    public void setIfBastic(String ifBastic) {
        this.ifBastic = ifBastic == null ? null : ifBastic.trim();
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
        sb.append(", shopId=").append(shopId);
        sb.append(", propId=").append(propId);
        sb.append(", propName=").append(propName);
        sb.append(", propValueId=").append(propValueId);
        sb.append(", propValue=").append(propValue);
        sb.append(", propType=").append(propType);
        sb.append(", propValueType=").append(propValueType);
        sb.append(", propRtype=").append(propRtype);
        sb.append(", ifMust=").append(ifMust);
        sb.append(", ifCheck=").append(ifCheck);
        sb.append(", ifBastic=").append(ifBastic);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }
}
