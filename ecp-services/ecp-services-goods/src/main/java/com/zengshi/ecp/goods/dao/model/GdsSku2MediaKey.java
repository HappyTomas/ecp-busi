package com.zengshi.ecp.goods.dao.model;

import java.io.Serializable;

public class GdsSku2MediaKey implements Serializable {
    private Long skuId;

    private Long mediaId;

    private static final long serialVersionUID = 1L;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skuId=").append(skuId);
        sb.append(", mediaId=").append(mediaId);
        sb.append("]");
        return sb.toString();
    }
}
