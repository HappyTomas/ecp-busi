package com.zengshi.ecp.staff.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class SellerResDTO extends BaseResponseDTO{

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<ShopInfoResDTO> getShoplist() {
        return shoplist;
    }

    public void setShoplist(List<ShopInfoResDTO> shoplist) {
        this.shoplist = shoplist;
    }


    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    /**
     * 企业Id
     */
    private Long companyId;
    
    /**
     * 店铺列表
     */
    private List<ShopInfoResDTO> shoplist;
    
    /**
     *是否卖家标识：0否 1是 
     */
    private String shopFlag;
    
    /**
     * 用户类型:10普通 20企业 30企业管理员
     */
    private String custType;


    public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(String shopFlag) {
        this.shopFlag = shopFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shoplist=").append(shoplist);
        sb.append(", companyId=").append(companyId);
        sb.append("]");
        return sb.toString();
    }
}

