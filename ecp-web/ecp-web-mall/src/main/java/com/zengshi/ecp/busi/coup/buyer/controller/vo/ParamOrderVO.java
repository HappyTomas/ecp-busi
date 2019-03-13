package com.zengshi.ecp.busi.coup.buyer.controller.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class ParamOrderVO extends EcpBasePageReqVO {

    private static final long serialVersionUID = 1L;
    private String spCode;
    private String spLang;
    private String spValue;
    private String spOrder;
    private String paramName;
    private String paramLinkKey;
    public String getSpCode() {
        return spCode;
    }
    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }
    public String getSpLang() {
        return spLang;
    }
    public void setSpLang(String spLang) {
        this.spLang = spLang;
    }
    public String getSpValue() {
        return spValue;
    }
    public void setSpValue(String spValue) {
        this.spValue = spValue;
    }
    public String getSpOrder() {
        return spOrder;
    }
    public void setSpOrder(String spOrder) {
        this.spOrder = spOrder;
    }
    public String getParamName() {
        return paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    public String getParamLinkKey() {
        return paramLinkKey;
    }
    public void setParamLinkKey(String paramLinkKey) {
        this.paramLinkKey = paramLinkKey;
    }
}

