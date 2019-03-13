/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ProductInfoReqDto.java 
 * Package Name:com.zengshi.ecp.staff.dubbo.dto 
 * Date:2015年9月14日下午5:47:06 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月14日下午5:47:06  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public class ProductInfoReqDto  extends BaseInfo {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private long productNum;
    private long    productID;
    private String   productType;
    private long   productPrice;
    private String orderId;
    private String orderSubId;
    
    private String isbnCode;//ISBN号
    
    private String bookCode;//书码
    /** 
     * productID. 
     * 
     * @return  the productID 
     * @since   JDK 1.6 
     */
    public long getProductID() {
        return productID;
    }
    /** 
     * productID. 
     * 
     * @param   productID    the productID to set 
     * @since   JDK 1.6 
     */
    public void setProductID(long productID) {
        this.productID = productID;
    }
    /** 
     * productType. 
     * 
     * @return  the productType 
     * @since   JDK 1.6 
     */
    public String getProductType() {
        return productType;
    }
    /** 
     * productType. 
     * 
     * @param   productType    the productType to set 
     * @since   JDK 1.6 
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }
    /** 
     * productPrice. 
     * 
     * @return  the productPrice 
     * @since   JDK 1.6 
     */
    public long getProductPrice() {
        return productPrice;
    }
    /** 
     * productPrice. 
     * 
     * @param   productPrice    the productPrice to set 
     * @since   JDK 1.6 
     */
    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }
    /** 
     * productNum. 
     * 
     * @return  the productNum 
     * @since   JDK 1.6 
     */
    public long getProductNum() {
        return productNum;
    }
    /** 
     * productNum. 
     * 
     * @param   productNum    the productNum to set 
     * @since   JDK 1.6 
     */
    public void setProductNum(long productNum) {
        this.productNum = productNum;
    }
    /** 
     * orderId. 
     * 
     * @return  the orderId 
     * @since   JDK 1.6 
     */
    public String getOrderId() {
        return orderId;
    }
    /** 
     * orderId. 
     * 
     * @param   orderId    the orderId to set 
     * @since   JDK 1.6 
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    /** 
     * orderSubId. 
     * 
     * @return  the orderSubId 
     * @since   JDK 1.6 
     */
    public String getOrderSubId() {
        return orderSubId;
    }
    /** 
     * orderSubId. 
     * 
     * @param   orderSubId    the orderSubId to set 
     * @since   JDK 1.6 
     */
    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }
    public String getIsbnCode() {
        return isbnCode;
    }
    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }
    public String getBookCode() {
        return bookCode;
    }
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }
    
    
}

