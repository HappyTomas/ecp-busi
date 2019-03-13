package com.zengshi.ecp.search.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class SecArgsRespDTO extends BaseResponseDTO{
    
private static final long serialVersionUID = 1L;
    
    private String argsKey;

    private String argsName;

    private String argsValue;

    private String argsDesc;

    private String status;

    public String getArgsKey() {
        return argsKey;
    }

    public void setArgsKey(String argsKey) {
        this.argsKey = argsKey == null ? null : argsKey.trim();
    }

    public String getArgsName() {
        return argsName;
    }

    public void setArgsName(String argsName) {
        this.argsName = argsName == null ? null : argsName.trim();
    }

    public String getArgsValue() {
        return argsValue;
    }

    public void setArgsValue(String argsValue) {
        this.argsValue = argsValue == null ? null : argsValue.trim();
    }

    public String getArgsDesc() {
        return argsDesc;
    }

    public void setArgsDesc(String argsDesc) {
        this.argsDesc = argsDesc == null ? null : argsDesc.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", argsKey=").append(argsKey);
        sb.append(", argsName=").append(argsName);
        sb.append(", argsValue=").append(argsValue);
        sb.append(", argsDesc=").append(argsDesc);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

}

