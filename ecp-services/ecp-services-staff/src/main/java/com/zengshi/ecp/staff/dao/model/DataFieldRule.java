package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;

public class DataFieldRule implements Serializable {
    private Long id;

    private Long itemId;

    private Long authId;

    private String valueFormate;

    private String inputValue;

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
}