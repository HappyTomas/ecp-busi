package com.zengshi.ecp.busi.goods.discountBlacklist.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsCatgCustBlacklistVO extends EcpBasePageReqVO {

    /** 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1L;

    private Long id;
    
    private String idsStr;
    
    private Long shopId;
    
    private Long gdsId;
    
    private String gdsIdsStr;

    private String status;
    
    private String gdsName;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdsStr() {
        return idsStr;
    }

    public void setIdsStr(String idsStr) {
        this.idsStr = idsStr;
    }

    public Long getGdsId() {
		return gdsId;
	}

	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}

    public String getGdsIdsStr() {
        return gdsIdsStr;
    }

    public void setGdsIdsStr(String gdsIdsStr) {
        this.gdsIdsStr = gdsIdsStr;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopId=").append(shopId);
        sb.append(", status=").append(status);
        sb.append(", gdsId=").append(gdsId);
        sb.append(", gdsName=").append(gdsName);
        sb.append("]");
        return sb.toString();
    }


    
}

