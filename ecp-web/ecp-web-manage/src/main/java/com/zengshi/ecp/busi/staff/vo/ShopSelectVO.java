package com.zengshi.ecp.busi.staff.vo;

import java.io.Serializable;

public class ShopSelectVO implements Serializable{/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private String shopName;
    private String shopStatus;
    private String companyID;
    private String companyName;
    private String hotShowSupported;
    
    /** 
     * shopName. 
     * 
     * @return  the shopName 
     * @since   JDK 1.6 
     */
    public String getShopName() {
        return shopName;
    }
    /** 
     * shopName. 
     * 
     * @param   shopName    the shopName to set 
     * @since   JDK 1.6 
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    
    
    /** 
     * shopStatus. 
     * 
     * @return  the shopStatus 
     * @since   JDK 1.6 
     */
    public String getShopStatus() {
        return shopStatus;
    }
    /** 
     * shopStatus. 
     * 
     * @param   shopStatus    the shopStatus to set 
     * @since   JDK 1.6 
     */
    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }
    /** 
     * companyID. 
     * 
     * @return  the companyID 
     * @since   JDK 1.6 
     */
    public String getCompanyID() {
        return companyID;
    }
    /** 
     * companyID. 
     * 
     * @param   companyID    the companyID to set 
     * @since   JDK 1.6 
     */
    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
    /** 
     * companyName. 
     * 
     * @return  the companyName 
     * @since   JDK 1.6 
     */
    public String getCompanyName() {
        return companyName;
    }
    /** 
     * companyName. 
     * 
     * @param   companyName    the companyName to set 
     * @since   JDK 1.6 
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
	public String getHotShowSupported() {
		return hotShowSupported;
	}
	public void setHotShowSupported(String hotShowSupported) {
		this.hotShowSupported = hotShowSupported;
	}
    
}



