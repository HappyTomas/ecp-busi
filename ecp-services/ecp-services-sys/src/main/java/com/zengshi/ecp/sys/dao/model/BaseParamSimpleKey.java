package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;

public class BaseParamSimpleKey implements Serializable {
    private String paramLinkKey;
    
    private String spCode;
    
    private String spLang;
    
    private static final long serialVersionUID = 1L;

    public String getParamLinkKey() {
        return paramLinkKey;
    }

    public void setParamLinkKey(String paramLinkKey) {
        this.paramLinkKey = paramLinkKey == null ? null : paramLinkKey.trim();
    }

    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode == null ? null : spCode.trim();
    }

    public String getSpLang() {
        return spLang;
    }

    public void setSpLang(String spLang) {
        this.spLang = spLang == null ? null : spLang.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paramLinkKey=").append(paramLinkKey);
        sb.append(", spCode=").append(spCode);
        sb.append(", spLang=").append(spLang);
        sb.append("]");
        return sb.toString();
    }
}
