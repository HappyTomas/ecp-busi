package com.zengshi.ecp.staff.dubbo.dto;

import java.math.BigDecimal;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class DataRuleResDTO extends BaseResponseDTO {
    private Long id;

    private Long itemId;

    private Long authId;

    private String operChar;

    private String logicChar;

    private String inputValue;

    private String frontChar;

    private String postChar;

    private BigDecimal orderBy;
    
    //add
    private String itemName;

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
    /*
    @Override
    public boolean equals(Object o){
        if(this == o)      //先检查是否其自反性，后比较o是否为空。这样效率高
            return true;
        if(o == null)         
            return false;
        if( !(o instanceof DataRuleResDTO))
            return false;
        
        final DataRuleResDTO dto = (DataRuleResDTO)o;
        
        if(!getId().equals(dto.getId()))
            return false;
        if(!getAuthId().equals(dto.getAuthId()))
            return false;
        if(!getItemId().equals(dto.getItemId()))
            return false;
        if(!getFrontChar().equals(dto.getFrontChar()))
            return false;
        if(!getInputValue().equals(dto.getInputValue()))
            return false;
        if(!getLogicChar().equals(dto.getLogicChar()))
            return false;
        if(!getOperChar().equals(dto.getOperChar()))
            return false;
        if(!getPostChar().equals(dto.getPostChar()))
            return false;
        if(!getOrderBy().equals(dto.getOrderBy()))
            return false;
        
        return true;
    }
    
    @Override
    public int hashCode(){
        int result = 17;
        result = 37 * result + id.intValue();
        result = 37 * result + itemId.intValue();
        result = 37 * result + authId.intValue();
        result = 37 * result + operChar.hashCode();
        result = 37 * result + logicChar.hashCode();
        result = 37 * result + inputValue.hashCode();
        result = 37 * result + frontChar.hashCode();
        result = 37 * result + postChar.hashCode();
        result = 37 * result + orderBy.hashCode();
        return result;
    }
    */

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
}