package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class DataRule implements Serializable {
    private Long id;

    private Long itemId;

    private Long authId;

    private String operChar;

    private String logicChar;

    private String inputValue;

    private String frontChar;

    private String postChar;

    private BigDecimal orderBy;

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

    public String getOperChar() {
        return operChar;
    }

    public void setOperChar(String operChar) {
        this.operChar = operChar == null ? null : operChar.trim();
    }

    public String getLogicChar() {
        return logicChar;
    }

    public void setLogicChar(String logicChar) {
        this.logicChar = logicChar == null ? null : logicChar.trim();
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue == null ? null : inputValue.trim();
    }

    public String getFrontChar() {
        return frontChar;
    }

    public void setFrontChar(String frontChar) {
        this.frontChar = frontChar == null ? null : frontChar.trim();
    }

    public String getPostChar() {
        return postChar;
    }

    public void setPostChar(String postChar) {
        this.postChar = postChar == null ? null : postChar.trim();
    }

    public BigDecimal getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(BigDecimal orderBy) {
        this.orderBy = orderBy;
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
        sb.append(", operChar=").append(operChar);
        sb.append(", logicChar=").append(logicChar);
        sb.append(", inputValue=").append(inputValue);
        sb.append(", frontChar=").append(frontChar);
        sb.append(", postChar=").append(postChar);
        sb.append(", orderBy=").append(orderBy);
        sb.append("]");
        return sb.toString();
    }
}