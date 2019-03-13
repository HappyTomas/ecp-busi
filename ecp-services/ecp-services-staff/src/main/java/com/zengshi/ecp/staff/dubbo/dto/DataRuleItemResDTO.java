package com.zengshi.ecp.staff.dubbo.dto;

import java.math.BigDecimal;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class DataRuleItemResDTO extends BaseResponseDTO {
	private Long id;

    private String name;

    private String fieldName;

    private String attrName;

    private String valueType;

    private String viewType;

    private String defaultValue;

    private String initValue;

    private String valueFormate;

    private BigDecimal orderBy;

    private Long funcId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType == null ? null : valueType.trim();
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType == null ? null : viewType.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public String getInitValue() {
        return initValue;
    }

    public void setInitValue(String initValue) {
        this.initValue = initValue == null ? null : initValue.trim();
    }

    public String getValueFormate() {
        return valueFormate;
    }

    public void setValueFormate(String valueFormate) {
        this.valueFormate = valueFormate == null ? null : valueFormate.trim();
    }

    public BigDecimal getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(BigDecimal orderBy) {
        this.orderBy = orderBy;
    }

    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", fieldName=").append(fieldName);
        sb.append(", attrName=").append(attrName);
        sb.append(", valueType=").append(valueType);
        sb.append(", viewType=").append(viewType);
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", initValue=").append(initValue);
        sb.append(", valueFormate=").append(valueFormate);
        sb.append(", orderBy=").append(orderBy);
        sb.append(", funcId=").append(funcId);
        sb.append("]");
        return sb.toString();
    }
}