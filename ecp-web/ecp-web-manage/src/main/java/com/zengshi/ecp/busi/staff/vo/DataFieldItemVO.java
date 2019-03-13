package com.zengshi.ecp.busi.staff.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class DataFieldItemVO extends EcpBasePageReqVO {
    private Long id;

    private String name;

    private String attrName;

    private String valueType;

    private String valueFormate;

    private String defaultValue;

    private Long funcId;
    
    private Long listAll;//当listAll为1 或不为空时作全集查询

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

    public String getValueFormate() {
        return valueFormate;
    }

    public void setValueFormate(String valueFormate) {
        this.valueFormate = valueFormate == null ? null : valueFormate.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
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
        sb.append(", attrName=").append(attrName);
        sb.append(", valueType=").append(valueType);
        sb.append(", valueFormate=").append(valueFormate);
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", funcId=").append(funcId);
        sb.append("]");
        return sb.toString();
    }

	public Long getListAll() {
		return listAll;
	}

	public void setListAll(Long listAll) {
		this.listAll = listAll;
	}
}