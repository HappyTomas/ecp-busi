package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class PayIntfReqLogWithBLOBs extends PayIntfReqLog implements Serializable {
    private String requestMsg;

    private String responseMsg;

    private static final long serialVersionUID = 1L;

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg == null ? null : requestMsg.trim();
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg == null ? null : responseMsg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", requestMsg=").append(requestMsg);
        sb.append(", responseMsg=").append(responseMsg);
        sb.append("]");
        return sb.toString();
    }
}