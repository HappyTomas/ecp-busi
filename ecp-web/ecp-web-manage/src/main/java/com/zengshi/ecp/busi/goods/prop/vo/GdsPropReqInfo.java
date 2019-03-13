package com.zengshi.ecp.busi.goods.prop.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsPropReqInfo extends  BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -6620744971867241470L;
    @NotBlank(message="{goods.GdsPropReqInfo.propName.null.error}")
    private String propName;

    private String propSname;
    
    /**
     * 属性值列表
     */
    @NotBlank(message="{goods.GdsPropReqInfo.propValuesStr.null.error}")
    private String propValuesStr;
    @NotBlank(message="{goods.GdsPropReqInfo.propValueType.null.error}")  
    private String propValueType;
    @NotBlank(message="{goods.GdsPropReqInfo.propType.null.error}")
    private String propType;

    private String propDesc;
    
    private Integer sortNo;
    
    /**
     * 属性值输入类型
     */
    private String propInputType;

    /**
     * 属性校验规则
     */
    private String propInputRule;
    
    private Long id;
    //需要删除的属性值id
    private String delPropValIds;

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropSname() {
        return propSname;
    }

    public void setPropSname(String propSname) {
        this.propSname = propSname;
    }

 

    public String getPropValueType() {
        return propValueType;
    }

    public void setPropValueType(String propValueType) {
        this.propValueType = propValueType;
    }

    public String getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType;
    }

    public String getPropDesc() {
        return propDesc;
    }

    public void setPropDesc(String propDesc) {
        this.propDesc = propDesc;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getPropValuesStr() {
        return propValuesStr;
    }

    public void setPropValuesStr(String propValuesStr) {
        this.propValuesStr = propValuesStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDelPropValIds() {
        return delPropValIds;
    }

    public void setDelPropValIds(String delPropValIds) {
        this.delPropValIds = delPropValIds;
    }

    @Override
    public String toString() {
        return "GdsPropReqInfo [propName=" + propName + ", propSname=" + propSname
                + ", propValuesStr=" + propValuesStr + ", propValueType=" + propValueType
                + ", propType=" + propType + ", propDesc=" + propDesc + ", sortNo=" + sortNo
                + ", id=" + id + ", delPropValIds=" + delPropValIds + "]";
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

