package com.zengshi.ecp.order.dao.model;

import java.io.Serializable;

public class OrdExpressReqLogWithBLOBs extends OrdExpressReqLog implements Serializable {
    private byte[] reqParam;

    private byte[] respParam;

    private static final long serialVersionUID = 1L;

    public byte[] getReqParam() {
        return reqParam;
    }

    public void setReqParam(byte[] reqParam) {
        this.reqParam = reqParam;
    }

    public byte[] getRespParam() {
        return respParam;
    }

    public void setRespParam(byte[] respParam) {
        this.respParam = respParam;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reqParam=").append(reqParam);
        sb.append(", respParam=").append(respParam);
        sb.append("]");
        return sb.toString();
    }
}