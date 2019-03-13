package com.zengshi.ecp.order.dubbo.dto;


import java.util.List;


import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;

public class RPreOrdMainResponse extends BaseResponseDTO {

    private Long staffId;
    
    private Long shopId;
    
    private String shopName;
    
    //运费
    private Long realExpressFee;
    
    
    private List<RPreOrdSubResponse> preOrdSubList;
    
    private List<AcctInfoResDTO> ordAcctInfoList;
    
    //订单原价
    private Long basicMoney;
    
    //订单金额
    private Long orderMoney;
    
    //订单数量
    private Long orderAmount;
    
    
    // 发票类型---提交订单时候使用、
    private String invoiceType;

    // 普票信息---提交订单时候使用、
    private ROrdInvoiceCommRequest rOrdInvoiceCommRequest;

    // 增票信息---提交订单时候使用、
    private ROrdInvoiceTaxRequest rOrdInvoiceTaxRequest;

    // 主订单编号----提交订单时候，预生成
    private String orderId;
    
    //购物车ID
    private Long cartId;

    // 实付金额-----提交订单时候，在control层和计算做检验
    private Long realMoney;
    
    //组合商品信息
    private List<List<RPreOrdSubResponse>> groupLists;

    //优惠券信息
    private List<CoupCheckBeanRespDTO> coupCheckBeanRespDTOs;
    
    //虚拟产品标识
    private String isVirtual;
    
    //单品归属平台分类标识
    private String isPlatCatgs;
    

    private static final long serialVersionUID = 1L;




    public List<List<RPreOrdSubResponse>> getGroupLists() {
        return groupLists;
    }


    public void setGroupLists(List<List<RPreOrdSubResponse>> groupLists) {
        this.groupLists = groupLists;
    }

    public List<CoupCheckBeanRespDTO> getCoupCheckBeanRespDTOs() {
        return coupCheckBeanRespDTOs;
    }

    public void setCoupCheckBeanRespDTOs(List<CoupCheckBeanRespDTO> coupCheckBeanRespDTOs) {
        this.coupCheckBeanRespDTOs = coupCheckBeanRespDTOs;
    }

    public Long getCartId() {
        return cartId;
    }


    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }


    public Long getStaffId() {
        return staffId;
    }


    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }


    public List<RPreOrdSubResponse> getPreOrdSubList() {
        return preOrdSubList;
    }


    public void setPreOrdSubList(List<RPreOrdSubResponse> preOrdSubList) {
        this.preOrdSubList = preOrdSubList;
    }


    public Long getShopId() {
        return shopId;
    }


    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }


    public String getShopName() {
        return shopName;
    }


    public void setShopName(String shopName) {
        this.shopName = shopName;
    }






    public List<AcctInfoResDTO> getOrdAcctInfoList() {
        return ordAcctInfoList;
    }


    public void setOrdAcctInfoList(List<AcctInfoResDTO> ordAcctInfoList) {
        this.ordAcctInfoList = ordAcctInfoList;
    }


    public Long getRealExpressFee() {
        return realExpressFee;
    }


    public void setRealExpressFee(Long realExpressFee) {
        this.realExpressFee = realExpressFee;
    }



    public Long getOrderAmount() {
        return orderAmount;
    }


    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }


    public Long getOrderMoney() {
        return orderMoney;
    }


    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }


    public String getInvoiceType() {
        return invoiceType;
    }


    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }


    public ROrdInvoiceCommRequest getrOrdInvoiceCommRequest() {
        return rOrdInvoiceCommRequest;
    }


    public void setrOrdInvoiceCommRequest(ROrdInvoiceCommRequest rOrdInvoiceCommRequest) {
        this.rOrdInvoiceCommRequest = rOrdInvoiceCommRequest;
    }


    public ROrdInvoiceTaxRequest getrOrdInvoiceTaxRequest() {
        return rOrdInvoiceTaxRequest;
    }


    public void setrOrdInvoiceTaxRequest(ROrdInvoiceTaxRequest rOrdInvoiceTaxRequest) {
        this.rOrdInvoiceTaxRequest = rOrdInvoiceTaxRequest;
    }


    public String getOrderId() {
        return orderId;
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public Long getRealMoney() {
        return realMoney;
    }


    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }


    public Long getBasicMoney() {
        return basicMoney;
    }


    public void setBasicMoney(Long basicMoney) {
        this.basicMoney = basicMoney;
    }


	public String getIsVirtual() {
		return isVirtual;
	}


	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}
	
	public String getIsPlatCatgs() {
        return isPlatCatgs;
    }


    public void setIsPlatCatgs(String isPlatCatgs) {
        this.isPlatCatgs = isPlatCatgs;
    }
    
}

