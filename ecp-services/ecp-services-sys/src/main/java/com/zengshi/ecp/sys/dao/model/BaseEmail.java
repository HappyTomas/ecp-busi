package com.zengshi.ecp.sys.dao.model;

import java.io.Serializable;

public class BaseEmail implements Serializable {
    private String smtpServer;

    private Integer smtpPort;

    private String sendEmail;

    private String smtpUser;

    private String smtpPassword;

    private String recEmail;

    private static final long serialVersionUID = 1L;

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer == null ? null : smtpServer.trim();
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail == null ? null : sendEmail.trim();
    }

    public String getSmtpUser() {
        return smtpUser;
    }

    public void setSmtpUser(String smtpUser) {
        this.smtpUser = smtpUser == null ? null : smtpUser.trim();
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword == null ? null : smtpPassword.trim();
    }

    public String getRecEmail() {
        return recEmail;
    }

    public void setRecEmail(String recEmail) {
        this.recEmail = recEmail == null ? null : recEmail.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", smtpServer=").append(smtpServer);
        sb.append(", smtpPort=").append(smtpPort);
        sb.append(", sendEmail=").append(sendEmail);
        sb.append(", smtpUser=").append(smtpUser);
        sb.append(", smtpPassword=").append(smtpPassword);
        sb.append(", recEmail=").append(recEmail);
        sb.append("]");
        return sb.toString();
    }
}
