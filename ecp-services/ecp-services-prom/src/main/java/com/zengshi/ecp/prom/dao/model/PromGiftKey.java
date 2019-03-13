package com.zengshi.ecp.prom.dao.model;

import java.io.Serializable;

public class PromGiftKey implements Serializable {
    private Long promId;

    private Long giftId;

    private static final long serialVersionUID = 1L;

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", promId=").append(promId);
        sb.append(", giftId=").append(giftId);
        sb.append("]");
        return sb.toString();
    }
}