package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuditAutoLog implements Serializable {
    private Long id;

    private String execMethods;

    private String stepDesc;

    private String payWay;

    private Timestamp executeTime;

    private String serverPid;

    private String serverHostip;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExecMethods() {
        return execMethods;
    }

    public void setExecMethods(String execMethods) {
        this.execMethods = execMethods == null ? null : execMethods.trim();
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc == null ? null : stepDesc.trim();
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public Timestamp getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Timestamp executeTime) {
        this.executeTime = executeTime;
    }

    public String getServerPid() {
        return serverPid;
    }

    public void setServerPid(String serverPid) {
        this.serverPid = serverPid == null ? null : serverPid.trim();
    }

    public String getServerHostip() {
        return serverHostip;
    }

    public void setServerHostip(String serverHostip) {
        this.serverHostip = serverHostip == null ? null : serverHostip.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", execMethods=").append(execMethods);
        sb.append(", stepDesc=").append(stepDesc);
        sb.append(", payWay=").append(payWay);
        sb.append(", executeTime=").append(executeTime);
        sb.append(", serverPid=").append(serverPid);
        sb.append(", serverHostip=").append(serverHostip);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}