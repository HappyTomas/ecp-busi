package com.zengshi.ecp.goods.dubbo.dto.gdsinfores;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
public class GdsSku2PropRespDTO extends BaseResponseDTO    {
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 2250757294107059804L;

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

    private String ifMust;

    private String ifCheck;

    private String ifBasic;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String propMediaUuid;


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

    public String getPropMediaUuid() {
		return propMediaUuid;
	}

	public void setPropMediaUuid(String propMediaUuid) {
		this.propMediaUuid = propMediaUuid;
	}

	public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
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

}
