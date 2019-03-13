package com.zengshi.ecp.goods.dubbo.dto.gdsinfores;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;
public class GdsSku2PropReqDTO extends BaseInfo    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 5581416786712358288L;

	private Long skuId;
	
	private Long gdsId;

    private Long  shopId;

    private Long propId;

    private String propName;

    private Long propValueId;

    private String propValue;

    private String propType;

    private String propValueType;
    
    private String propInputType;

    private String propInputRule;
    
    private String propRtype;

    private String ifMust;

    private String ifCheck;

    private String ifBasic;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String gdsStatus;
    
    /**
     * 需要查询的属性列表（查询用）
     */
    private List<Long> propIds;
    
    /**
     * 只查询对应属性类型（查询用）
     */
    private List<String> propTypes;
    
    /**
     * 只查询对应属性值类型（查询用）
     */
    private List<String> propValueTypes;

    /**
     * 只查询对应属性属性类型（查询用）
     */
    private List<String> propInputTypes;


    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
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

    public String getIfBasic() {
        return ifBasic;
    }

    public void setIfBasic(String ifBasic) {
        this.ifBasic = ifBasic == null ? null : ifBasic.trim();
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

    public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skuId=").append(skuId);
        sb.append(", shopId=").append(shopId);
        sb.append(", propId=").append(propId);
        sb.append(", propName=").append(propName);
        sb.append(", propValueId=").append(propValueId);
        sb.append(", propValue=").append(propValue);
        sb.append(", propType=").append(propType);
        sb.append(", propValueType=").append(propValueType);
        sb.append(", ifMust=").append(ifMust);
        sb.append(", ifCheck=").append(ifCheck);
        sb.append(", ifBasic=").append(ifBasic);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append("]");
        return sb.toString();
    }

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }

	public String getPropInputType() {
		return propInputType;
	}

	public void setPropInputType(String propInputType) {
		this.propInputType = propInputType;
	}

	public String getPropInputRule() {
		return propInputRule;
	}

	public void setPropInputRule(String propInputRule) {
		this.propInputRule = propInputRule;
	}

    public List<Long> getPropIds() {
        return propIds;
    }

    public void setPropIds(List<Long> propIds) {
        this.propIds = propIds;
    }

    public String getPropRtype() {
        return propRtype;
    }

    public void setPropRtype(String propRtype) {
        this.propRtype = propRtype;
    }

    public List<String> getPropTypes() {
        return propTypes;
    }

    public void setPropTypes(List<String> propTypes) {
        this.propTypes = propTypes;
    }

    public List<String> getPropValueTypes() {
        return propValueTypes;
    }

    public void setPropValueTypes(List<String> propValueTypes) {
        this.propValueTypes = propValueTypes;
    }

    public List<String> getPropInputTypes() {
        return propInputTypes;
    }

    public void setPropInputTypes(List<String> propInputTypes) {
        this.propInputTypes = propInputTypes;
    }
}
