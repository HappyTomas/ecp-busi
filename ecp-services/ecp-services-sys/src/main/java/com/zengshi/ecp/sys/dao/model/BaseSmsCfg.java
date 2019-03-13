package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;

public class BaseSmsCfg implements Serializable {
    private String smsGateway;

    private String url;

    private String account;

    private String authKey;

    private String othParams;

    private static final long serialVersionUID = 1L;

    public String getSmsGateway() {
        return smsGateway;
    }

    public void setSmsGateway(String smsGateway) {
        this.smsGateway = smsGateway == null ? null : smsGateway.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey == null ? null : authKey.trim();
    }

    public String getOthParams() {
        return othParams;
    }

    public void setOthParams(String othParams) {
        this.othParams = othParams == null ? null : othParams.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", smsGateway=").append(smsGateway);
        sb.append(", url=").append(url);
        sb.append(", account=").append(account);
        sb.append(", authKey=").append(authKey);
        sb.append(", othParams=").append(othParams);
        sb.append("]");
        return sb.toString();
    }
}
