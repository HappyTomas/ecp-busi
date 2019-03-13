package com.zengshi.ecp.busi.prom.createprom.vo;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-21 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromRuleVO implements Serializable{
     /**
      * 促销规则vo
      */

    private static final long serialVersionUID = 1L;

    private String custGroup;

    private String custGroupValue;

    private String custLevel;

    private String custLevelValue;

    private String channel;

    private String channelValue;

    private String area;

    private String areaValue;
    
    private String areaExclude;//1 表示areaValue 区域外的有效  否则 areaValue有效

    private String limitTimesType;// 限制次数类型 0无限制 1天 2月 3年

    @DecimalMin(value="1",message="{prom.promRuleVO.limitTimesTypeValue.1.error}")
    private String limitTimesTypeValue;// 限制次数

    private String limitTotalType;// 限制总量类型 0无限制 1天 2月 3年

    @DecimalMin(value="1",message="{prom.promRuleVO.limitTotalTypeValue.1.error}")
    private String limitTotalTypeValue;// 限制总量

    @DecimalMin(value="1",message="{prom.promRuleVO.minBuyCnt.1.error}")
    private String minBuyCnt;// 限制最小量
    
    @DecimalMin(value="1",message="{prom.promRuleVO.maxBuyCnt.1.error}")
    private String maxBuyCnt;// 限制最大购买量
    

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
    }

    public String getCustGroupValue() {
        return custGroupValue;
    }

    public void setCustGroupValue(String custGroupValue) {
        this.custGroupValue = custGroupValue;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustLevelValue() {
        return custLevelValue;
    }

    public void setCustLevelValue(String custLevelValue) {
        this.custLevelValue = custLevelValue;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelValue() {
        return channelValue;
    }

    public void setChannelValue(String channelValue) {
        this.channelValue = channelValue;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaValue() {
        return areaValue;
    }

    public void setAreaValue(String areaValue) {
        this.areaValue = areaValue;
    }

    public String getLimitTimesType() {
        return limitTimesType;
    }

    public void setLimitTimesType(String limitTimesType) {
        this.limitTimesType = limitTimesType;
    }

    public String getLimitTimesTypeValue() {
        return limitTimesTypeValue;
    }

    public void setLimitTimesTypeValue(String limitTimesTypeValue) {
        this.limitTimesTypeValue = limitTimesTypeValue;
    }

    public String getLimitTotalType() {
        return limitTotalType;
    }

    public void setLimitTotalType(String limitTotalType) {
        this.limitTotalType = limitTotalType;
    }

    public String getLimitTotalTypeValue() {
        return limitTotalTypeValue;
    }

    public void setLimitTotalTypeValue(String limitTotalTypeValue) {
        this.limitTotalTypeValue = limitTotalTypeValue;
    }

    public String getMinBuyCnt() {
        return minBuyCnt;
    }

    public void setMinBuyCnt(String minBuyCnt) {
        this.minBuyCnt = minBuyCnt;
    }

    public String getMaxBuyCnt() {
        return maxBuyCnt;
    }

    public void setMaxBuyCnt(String maxBuyCnt) {
        this.maxBuyCnt = maxBuyCnt;
    }

    public String getAreaExclude() {
        return areaExclude;
    }

    public void setAreaExclude(String areaExclude) {
        this.areaExclude = areaExclude;
    }

}
