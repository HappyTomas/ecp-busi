package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class AuditHostConfig implements Serializable {
    private String id;

    private String auditAcctname;

    private String payWay;

    private String connType;

    private String serverHost;

    private Integer serverPort;

    private String loginUsername;

    private String loginPassword;

    private String remotePath;

    private String filenameCode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAuditAcctname() {
        return auditAcctname;
    }

    public void setAuditAcctname(String auditAcctname) {
        this.auditAcctname = auditAcctname == null ? null : auditAcctname.trim();
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getConnType() {
        return connType;
    }

    public void setConnType(String connType) {
        this.connType = connType == null ? null : connType.trim();
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost == null ? null : serverHost.trim();
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername == null ? null : loginUsername.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath == null ? null : remotePath.trim();
    }

    public String getFilenameCode() {
        return filenameCode;
    }

    public void setFilenameCode(String filenameCode) {
        this.filenameCode = filenameCode == null ? null : filenameCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", auditAcctname=").append(auditAcctname);
        sb.append(", payWay=").append(payWay);
        sb.append(", connType=").append(connType);
        sb.append(", serverHost=").append(serverHost);
        sb.append(", serverPort=").append(serverPort);
        sb.append(", loginUsername=").append(loginUsername);
        sb.append(", loginPassword=").append(loginPassword);
        sb.append(", remotePath=").append(remotePath);
        sb.append(", filenameCode=").append(filenameCode);
        sb.append("]");
        return sb.toString();
    }
}