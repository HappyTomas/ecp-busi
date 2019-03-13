package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;

public class RSumbitMainRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 712882826760800117L;
    
    /** 
     * cartId:购物车ID. 
     * @since JDK 1.6 
     */ 
    private Long cartId;

    private Long staffId;

    private Long shopId;

    private String shopName;
    
    //优惠码参数
    private String coupCode;
    
    //优惠码金额
    private Long coupCodeMoney;

    // 运费
    private Long realExpressFee;

    private List<RSumbitSubRequest> preOrdSubList;

    private List<AcctInfoResDTO> ordAcctInfoList;
    
    //促销相关信息
    private CartPromRespDTO  cartPromRespDTO;

    // 订单促销优惠金额
    private Long ordPromReduceMoney;
    
    //订单原价
    private Long basicMoney;

    // 订单金额
    private Long orderMoney;
    
    // 实付金额
    private Long realMoney;   

    // 订单数量
    private Long orderAmount;

    // 订单id
    private String orderId;

    // 发票类型---提交订单时候使用、
    private String invoiceType;

    // 普票信息---提交订单时候使用、
    private ROrdInvoiceCommRequest rOrdInvoiceCommRequest;

    // 增票信息---提交订单时候使用、
    private ROrdInvoiceTaxRequest rOrdInvoiceTaxRequest;
    
    //支付方式---提交订单时候使用、
    private String payType;
    
    //配送方式---提交订单使用
    private String deliverType;

    private String buyerMsg;

    //优惠券使用
    private List<CoupCheckBeanRespDTO> coupCheckBean;

    public String getBuyerMsg() {
        return buyerMsg;
    }

    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public CartPromRespDTO getCartPromRespDTO() {
        return cartPromRespDTO;
    }

    public void setCartPromRespDTO(CartPromRespDTO cartPromRespDTO) {
        this.cartPromRespDTO = cartPromRespDTO;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public List<RSumbitSubRequest> getPreOrdSubList() {
        return preOrdSubList;
    }

    public void setPreOrdSubList(List<RSumbitSubRequest> preOrdSubList) {
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

    public Long getOrdPromReduceMoney() {
        return ordPromReduceMoney;
    }

    public void setOrdPromReduceMoney(Long ordPromReduceMoney) {
        this.ordPromReduceMoney = ordPromReduceMoney;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType;
    }

    public Long getBasicMoney() {
        return basicMoney;
    }

    public void setBasicMoney(Long basicMoney) {
        this.basicMoney = basicMoney;
    }
    public List<CoupCheckBeanRespDTO> getCoupCheckBean() {
        return coupCheckBean;
    }

    public void setCoupCheckBean(List<CoupCheckBeanRespDTO> coupCheckBean) {
        this.coupCheckBean = coupCheckBean;
    }

    public String getCoupCode() {
        return coupCode;
    }

    public void setCoupCode(String coupCode) {
        this.coupCode = coupCode;
    }

    public Long getCoupCodeMoney() {
        return coupCodeMoney;
    }

    public void setCoupCodeMoney(Long coupCodeMoney) {
        this.coupCodeMoney = coupCodeMoney;
    }
}
