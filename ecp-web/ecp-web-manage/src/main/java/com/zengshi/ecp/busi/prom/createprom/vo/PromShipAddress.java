package com.zengshi.ecp.busi.prom.createprom.vo;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class PromShipAddress extends BaseResponseDTO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 4278919350319026228L;
    
    private String areaCode;
    
    private String areaName;
    
    private String parentAreaCode;
    
    private String areaCodeShort;
    
    private String areaLevel;
    
    private String centerFlag;
    
    private int areaOrder;
    
    private List<BaseAreaAdminRespDTO> list;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentAreaCode() {
        return parentAreaCode;
    }

    public void setParentAreaCode(String parentAreaCode) {
        this.parentAreaCode = parentAreaCode;
    }

    public String getAreaCodeShort() {
        return areaCodeShort;
    }

    public void setAreaCodeShort(String areaCodeShort) {
        this.areaCodeShort = areaCodeShort;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getCenterFlag() {
        return centerFlag;
    }

    public void setCenterFlag(String centerFlag) {
        this.centerFlag = centerFlag;
    }

    public int getAreaOrder() {
        return areaOrder;
    }

    public void setAreaOrder(int areaOrder) {
        this.areaOrder = areaOrder;
    }

    public List<BaseAreaAdminRespDTO> getList() {
        return list;
    }

    public void setList(List<BaseAreaAdminRespDTO> list) {
        this.list = list;
    }

    
    
}

