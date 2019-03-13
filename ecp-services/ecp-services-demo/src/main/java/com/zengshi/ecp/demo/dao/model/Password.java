package com.zengshi.ecp.demo.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Password implements Serializable {
    private String userId;

    private String password;

    private Timestamp validDate;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Timestamp getValidDate() {
        return validDate;
    }

    public void setValidDate(Timestamp validDate) {
        this.validDate = validDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", password=").append(password);
        sb.append(", validDate=").append(validDate);
        sb.append("]");
        return sb.toString();
    }
}
