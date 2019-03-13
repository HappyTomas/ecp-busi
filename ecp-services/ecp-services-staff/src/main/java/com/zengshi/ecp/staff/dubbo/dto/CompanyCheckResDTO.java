package com.zengshi.ecp.staff.dubbo.dto;

import java.io.Serializable;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CompanyCheckResDTO extends BaseResponseDTO implements Serializable {
 
    /** 
     * serialVersionUID:TODO(企业审核后返回的参数). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

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

    public boolean isIfNewCompany() {
        return ifNewCompany;
    }

    public void setIfNewCompany(boolean ifNewCompany) {
        this.ifNewCompany = ifNewCompany;
    }

    private Long shopId;
    
    private Long companyId;
    
    private boolean ifNewCompany;
    
    private Long staffId;
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    private String companyName;
    
    private String shopName;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    
    
    
}