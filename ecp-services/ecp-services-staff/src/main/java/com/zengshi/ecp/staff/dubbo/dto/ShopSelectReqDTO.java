package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ShopSelectReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(接受前店的店铺查询条件). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    
    private String shopName;
    private Long shopId;
    private String companyName;
    private Long companyId;
    private String status;
    private String hotShowSupported;
    
    /**
     * 是否展示积分商品店铺  默认展示
     */
    private boolean isShowScoreShop=true;
    /**
     * 查询表的分表下标
     */
    private int tableIndex;

    

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
     * shopID. 
     * 
     * @return  the shopID 
     * @since   JDK 1.6 
     */
    public Long getShopId() {
        return shopId;
    }
    /** 
     * shopID. 
     * 
     * @param   shopID    the shopID to set 
     * @since   JDK 1.6 
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
    
    /** 
     * companyID. 
     * 
     * @return  the companyID 
     * @since   JDK 1.6 
     */
    public Long getCompanyID() {
        return companyId;
    }
    /** 
     * companyID. 
     * 
     * @param   companyID    the companyID to set 
     * @since   JDK 1.6 
     */
    public void setCompanyID(Long companyID) {
        this.companyId = companyID;
    }
    
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public String getHotShowSupported() {
		return hotShowSupported;
	}
	public void setHotShowSupported(String hotShowSupported) {
		this.hotShowSupported = hotShowSupported;
	}
	public int getTableIndex() {
        return tableIndex;
    }
    public void setTableIndex(int tableIndex) {
        this.tableIndex = tableIndex;
    }
    public boolean isShowScoreShop() {
		return isShowScoreShop;
	}
	public void setShowScoreShop(boolean isShowScoreShop) {
		this.isShowScoreShop = isShowScoreShop;
	}
	/** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopSelectCond [shopName=");
        builder.append(shopName);
        builder.append(", companyName=");
        builder.append(companyName);
        builder.append(", staffId=");
        builder.append(", staffPhone=");
        builder.append(", status=");
        builder.append(", hotShowSupported=");
        builder.append("]");
        return builder.toString();
    }
    
}

