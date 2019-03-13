package com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 
 * Title: ECP-商品属性关系入参 <br>
 * Project Name:com.zengshi.ecp.ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年4月20日下午2:47:32  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public class GdsSku2PropPropIdxReqDTO extends BaseInfo{
	
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 2702946803294660393L;

	/**
	 * 属性Id
	 */
	private Long propId;

	/**
	 * 单品Id
	 */
    private Long skuId;
    
    /**
     * 商品Id
     */
    private Long gdsId;

    /**
     * 店铺Id
     */
    private Long shopId;
    /**
     * 属性名称
     */
    private String propName;

    /**
     * 属性值Id
     */
    private Long propValueId;

    /**
     * 属性值
     */
    private String propValue;
    
    /**
     * 属性名称后匹配end
     */
    private String propValueELike;
    
    /**
     * 属性名称前匹配 prefix
     */
    private String propValuePLike;
    /**
     * 属性名称全匹配ALL
     */
    private String propValueALike;

    /**
     * 属性类型
     */
    private String propType;

    /**
     * 属性值类型
     */
    private String propValueType;

    /**
     * 属性图片id
     */
    private String propMediaUuid;

    /**
     * 是否必填
     */
    private String ifHaveto;

    /**
     * 是否选中（暂时没用）
     */
    private String ifCheck;

    /**
     * 是否基本属性
     */
    private String ifBasic;

    /**
     * 状态
     */
    private String status;

    /**
     * 商品状态
     */
    private String gdsStatus;

    /**
     * 商品输入类型
     */
    private String propInputType;

    /**
     * 商品输入校验
     */
    private String propInputRule;

    /**
     * 查询的单品参数
     */
    private SkuQueryOption[] options;

    /**
     * 需要查询的属性Id
     */
    private List<Long> propIds;
    /**
     * 开始时间
     */
    private Date beginTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 属性值空查询。
     */
    private Boolean propValueNullQuery;
    
    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private List<String> propValues;


    public Long getPropId() {
        return propId;
    }

    public void setPropId(Long propId) {
        this.propId = propId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
        this.propType = propType == null ? null : propType.trim();
    }

    public String getPropValueType() {
        return propValueType;
    }

    public void setPropValueType(String propValueType) {
        this.propValueType = propValueType == null ? null : propValueType.trim();
    }

    public String getPropMediaUuid() {
        return propMediaUuid;
    }

    public void setPropMediaUuid(String propMediaUuid) {
        this.propMediaUuid = propMediaUuid == null ? null : propMediaUuid.trim();
    }

    public String getIfHaveto() {
        return ifHaveto;
    }

    public void setIfHaveto(String ifHaveto) {
        this.ifHaveto = ifHaveto == null ? null : ifHaveto.trim();
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

	public SkuQueryOption[] getOptions() {
		return options;
	}

	public void setOptions(SkuQueryOption[] options) {
		this.options = options;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Boolean getPropValueNullQuery() {
		return propValueNullQuery;
	}

	public void setPropValueNullQuery(Boolean propValueNullQuery) {
		this.propValueNullQuery = propValueNullQuery;
	}

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getPropValueELike() {
        return propValueELike;
    }

    public void setPropValueELike(String propValueELike) {
        this.propValueELike = propValueELike;
    }

    public String getPropValuePLike() {
        return propValuePLike;
    }

    public void setPropValuePLike(String propValuePLike) {
        this.propValuePLike = propValuePLike;
    }

    public String getPropValueALike() {
        return propValueALike;
    }

    public void setPropValueALike(String propValueALike) {
        this.propValueALike = propValueALike;
    }


    public List<Long> getPropIds() {
        return propIds;
    }

    public void setPropIds(List<Long> propIds) {
        this.propIds = propIds;
    }

    public List<String> getPropValues() {
        return propValues;
    }

    public void setPropValues(List<String> propValues) {
        this.propValues = propValues;
    }
    
    
  
}
