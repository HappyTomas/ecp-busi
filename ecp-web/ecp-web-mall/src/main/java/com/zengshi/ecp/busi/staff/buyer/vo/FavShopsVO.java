package com.zengshi.ecp.busi.staff.buyer.vo;

import java.io.Serializable;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class FavShopsVO extends EcpBasePageReqVO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long shopId;
    
    private Long companyId;

    private String shopName;

    private String shopFullName;

    private String shopGrade;

    private String shopDesc;
    
    private long favCount;
    

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopFullName() {
        return shopFullName;
    }

    public void setShopFullName(String shopFullName) {
        this.shopFullName = shopFullName;
    }

    public String getShopGrade() {
        return shopGrade;
    }

    public void setShopGrade(String shopGrade) {
        this.shopGrade = shopGrade;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public long getFavCount() {
        return favCount;
    }

    public void setFavCount(long favCount) {
        this.favCount = favCount;
    }
    
    
}

