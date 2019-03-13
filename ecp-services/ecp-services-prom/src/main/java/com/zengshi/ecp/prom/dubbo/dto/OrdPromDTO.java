package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class OrdPromDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String cartType;
    
    private boolean cartInitAction = false;

    private String staffId;

    private Long shopId;

    private String shopName;

    private PromInfoDTO promInfoDTO;

    private Date createTime;

    private Date updateTime;

    private Long createStaff;

    private Long updateStaff;

    private PromTypeMsgResponseDTO promTypeMsgResponseDTO;

    private List<PromInfoDTO> promInfoDTOList;// 满足订单促销列表

    private List<OrdPromDetailDTO> ordPromDetailDTOList;

    private Date calDate;// 必传计算时间字段

    private BigDecimal discountMoney=BigDecimal.ZERO;// 优惠金额
    
    private BigDecimal discountPriceMoney=BigDecimal.ZERO;// 其他优惠金额  非单价优惠金额

    private BigDecimal discountAmount=BigDecimal.ZERO;// 优惠数量

    private boolean ifFulfillProm;// 是否满足促销
    
    private PromRuleCheckDTO promRuleCheckDTO;//促销规则 业务数据 例如用户信息等
    
    private List<PromGiftDTO> promGiftDTOList;//订单赠品列表
    
    private Long siteId;//站点编码   必传
    
    private Long orderMoney;//免邮传入
    
    private Date orderTime;
    
    private String orderId;
    
    //初始化基础信息
    public void setOrdPromDTOInfo(Long promId){
        //优惠信息  提醒信息
        this.setDiscountAmount(BigDecimal.ZERO);
        this.setDiscountMoney(BigDecimal.ZERO);
        this.setDiscountPriceMoney(BigDecimal.ZERO);
        if(StringUtil.isEmpty(promId)){
            this.setPromInfoDTO(null);
        }
        this.setIfFulfillProm(Boolean.FALSE);
        this.setPromTypeMsgResponseDTO(null);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public PromTypeMsgResponseDTO getPromTypeMsgResponseDTO() {
        return promTypeMsgResponseDTO;
    }

    public void setPromTypeMsgResponseDTO(PromTypeMsgResponseDTO promTypeMsgResponseDTO) {
        this.promTypeMsgResponseDTO = promTypeMsgResponseDTO;
    }

    public List<PromInfoDTO> getPromInfoDTOList() {
        return promInfoDTOList;
    }

    public void setPromInfoDTOList(List<PromInfoDTO> promInfoDTOList) {
        this.promInfoDTOList = promInfoDTOList;
    }

    public Date getCalDate() {
        return calDate;
    }

    public void setCalDate(Date calDate) {
        this.calDate = calDate;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        if(discountAmount==null){
            this.discountAmount=BigDecimal.ZERO;
        }else{
            this.discountAmount = discountAmount.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        if(discountMoney==null){
            this.discountMoney=BigDecimal.ZERO;
        }else{
            this.discountMoney = discountMoney.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getDiscountPriceMoney() {
        return discountPriceMoney;
    }

    public void setDiscountPriceMoney(BigDecimal discountPriceMoney) {
        if(discountPriceMoney==null){
            this.discountPriceMoney=BigDecimal.ZERO;
        }else{
            this.discountPriceMoney = discountPriceMoney.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }
    
    public boolean isIfFulfillProm() {
        return ifFulfillProm;
    }

    public void setIfFulfillProm(boolean ifFulfillProm) {
        this.ifFulfillProm = ifFulfillProm;
    }

    public List<OrdPromDetailDTO> getOrdPromDetailDTOList() {
        return ordPromDetailDTOList;
    }

    public void setOrdPromDetailDTOList(List<OrdPromDetailDTO> ordPromDetailDTOList) {
        this.ordPromDetailDTOList = ordPromDetailDTOList;
    }

    public PromRuleCheckDTO getPromRuleCheckDTO() {
        return promRuleCheckDTO;
    }

    public void setPromRuleCheckDTO(PromRuleCheckDTO promRuleCheckDTO) {
        this.promRuleCheckDTO = promRuleCheckDTO;
    }

    public List<PromGiftDTO> getPromGiftDTOList() {
        return promGiftDTOList;
    }

    public void setPromGiftDTOList(List<PromGiftDTO> promGiftDTOList) {
        this.promGiftDTOList = promGiftDTOList;
    }

    public PromInfoDTO getPromInfoDTO() {
        return promInfoDTO;
    }

    public void setPromInfoDTO(PromInfoDTO promInfoDTO) {
        this.promInfoDTO = promInfoDTO;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isCartInitAction() {
        return cartInitAction;
    }

    public void setCartInitAction(boolean cartInitAction) {
        this.cartInitAction = cartInitAction;
    }
    
}
