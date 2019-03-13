package com.zengshi.ecp.prom.dubbo.dto;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromRuleCheckDTO extends QueryPromDTO {

    private static final long serialVersionUID = 1L;

    private String custGroupValue; //必填参数

    private String custLevelValue; //必填参数

    private String channelValue;//必填参数

    private String areaValue;//必填参数
 
    private String buyCnt;// 当前购买量
    
    private String ifThrows;//是否抛出错误
    
    private String ifComposit;//不必填写，内部逻辑使用
    
    private String ifshowTime;// 1表示根据生效时间取值  非1使用展示时间
    
    private Boolean ifCalPrice=false;//促销在非生效时 是否计算价格  true需要计算 false不需要计算

    public String getCustGroupValue() {
        return custGroupValue;
    }

    public void setCustGroupValue(String custGroupValue) {
        this.custGroupValue = custGroupValue;
    }

    public String getCustLevelValue() {
        return custLevelValue;
    }

    public void setCustLevelValue(String custLevelValue) {
        this.custLevelValue = custLevelValue;
    }

    public String getChannelValue() {
        return channelValue;
    }

    public void setChannelValue(String channelValue) {
        this.channelValue = channelValue;
    }

    public String getAreaValue() {
        return areaValue;
    }

    public void setAreaValue(String areaValue) {
        this.areaValue = areaValue;
    }

    public String getBuyCnt() {
        return buyCnt;
    }

    public void setBuyCnt(String buyCnt) {
        this.buyCnt = buyCnt;
    }

    public String getIfThrows() {
        return ifThrows;
    }

    public void setIfThrows(String ifThrows) {
        this.ifThrows = ifThrows;
    }

    public String getIfComposit() {
        return ifComposit;
    }

    public void setIfComposit(String ifComposit) {
        this.ifComposit = ifComposit;
    }

    public String getIfshowTime() {
        return ifshowTime;
    }

    public void setIfshowTime(String ifshowTime) {
        this.ifshowTime = ifshowTime;
    }

	public Boolean getIfCalPrice() {
		return ifCalPrice;
	}

	public void setIfCalPrice(Boolean ifCalPrice) {
		this.ifCalPrice = ifCalPrice;
	}

  
}
