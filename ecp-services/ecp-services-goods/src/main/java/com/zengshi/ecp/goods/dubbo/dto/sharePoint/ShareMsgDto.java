package com.zengshi.ecp.goods.dubbo.dto.sharePoint;


public class ShareMsgDto{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.7 
     */ 
    private static final long serialVersionUID = 1L;
    /**
     * 商品ID
     */
    private String gdsId;
    /**
     * 单品ID
     */
    private String skuId;
    /**
     * 分享者ID
     */
    private String staffId;
	public String getGdsId() {
		return gdsId;
	}
	public void setGdsId(String gdsId) {
		this.gdsId = gdsId;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
    
   

    
    
}
