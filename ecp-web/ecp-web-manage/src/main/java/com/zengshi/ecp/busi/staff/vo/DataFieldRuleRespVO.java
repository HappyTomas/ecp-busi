package com.zengshi.ecp.busi.staff.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

public class DataFieldRuleRespVO extends EcpBaseResponseVO {
    private Long id;

    private Long itemId;

    private Long authId;

    private String valueFormate;

    private String inputValue;
    
    //item 对应 itemId
    private String itemName;
    
    private String itemAttrName;

    private String itemValueType;
    
    private String itemValueFormate;
    
    private String itemDefaultValue;
    
    private Long funcId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getValueFormate() {
        return valueFormate;
    }

    public void setValueFormate(String valueFormate) {
        this.valueFormate = valueFormate == null ? null : valueFormate.trim();
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue == null ? null : inputValue.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemId=").append(itemId);
        sb.append(", authId=").append(authId);
        sb.append(", valueFormate=").append(valueFormate);
        sb.append(", inputValue=").append(inputValue);
        sb.append("]");
        return sb.toString();
    }

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemAttrName() {
		return itemAttrName;
	}

	public void setItemAttrName(String itemAttrName) {
		this.itemAttrName = itemAttrName;
	}

	public String getItemValueType() {
		return itemValueType;
	}

	public void setItemValueType(String itemValueType) {
		this.itemValueType = itemValueType;
	}

	public String getItemValueFormate() {
		return itemValueFormate;
	}

	public void setItemValueFormate(String itemValueFormate) {
		this.itemValueFormate = itemValueFormate;
	}

	public String getItemDefaultValue() {
		return itemDefaultValue;
	}

	public void setItemDefaultValue(String itemDefaultValue) {
		this.itemDefaultValue = itemDefaultValue;
	}

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
}