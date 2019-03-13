package com.zengshi.ecp.staff.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class OrderInfoReqDTO   extends BaseInfo {
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private String    orderInfoID;
    private String    orderSonInfoID;

	private List<ProductInfoReqDto> productList;




    /** 
     * productList. 
     * 
     * @return  the productList 
     * @since   JDK 1.6 
     */
    public List<ProductInfoReqDto> getProductList() {
        return productList;
    }

    /** 
     * productList. 
     * 
     * @param   productList    the productList to set 
     * @since   JDK 1.6 
     */
    public void setProductList(List<ProductInfoReqDto> productList) {
        this.productList = productList;
    }

    /** 
     * orderInfoID. 
     * 
     * @return  the orderInfoID 
     * @since   JDK 1.6 
     */
    public String getOrderInfoID() {
        return orderInfoID;
    }

    /** 
     * orderInfoID. 
     * 
     * @param   orderInfoID    the orderInfoID to set 
     * @since   JDK 1.6 
     */
    public void setOrderInfoID(String orderInfoID) {
        this.orderInfoID = orderInfoID;
    }

    /** 
     * orderSonInfoID. 
     * 
     * @return  the orderSonInfoID 
     * @since   JDK 1.6 
     */
    public String getOrderSonInfoID() {
        return orderSonInfoID;
    }

    /** 
     * orderSonInfoID. 
     * 
     * @param   orderSonInfoID    the orderSonInfoID to set 
     * @since   JDK 1.6 
     */
    public void setOrderSonInfoID(String orderSonInfoID) {
        this.orderSonInfoID = orderSonInfoID;
    }
    
	
}
