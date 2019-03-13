package com.zengshi.ecp.unpf.dao.model;

import java.io.Serializable;

public class UnpfMsgSycHisWithBLOBs extends UnpfMsgSycHis implements Serializable {
    private String msg;

    private String errors;

    private static final long serialVersionUID = 1L;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors == null ? null : errors.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msg=").append(msg);
        sb.append(", errors=").append(errors);
        sb.append("]");
        return sb.toString();
    }
}