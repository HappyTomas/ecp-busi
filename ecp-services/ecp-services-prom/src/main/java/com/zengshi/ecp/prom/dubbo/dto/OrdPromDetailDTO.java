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
public class OrdPromDetailDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long cartId;

    private String orderId;
    
    private String subOrderId;
    
    private String cartType;

    private Long shopId;

    private String shopName;

    private Long gdsId;

    private String gdsName;

    private Long skuId;
    
    private String skuInfo;

    private String groupDetail;

    private String isPrimeGds;

    private Long orderAmount;

    private PromInfoDTO promInfoDTO;

    private String staffId;

    private Date addTime;

    private Date createTime;

    private Date updateTime;

    private String createStaff;

    private String updateStaff;

    private Long basePrice;// 基础单价
    
    private Long buyPrice;//购买价格
    
    private String priceType;//定价策略 商品提供 使用buyprice 还是basePrice

    private BigDecimal discountPrice = BigDecimal.ZERO;// 单价优惠 指优惠了多少单价
    private BigDecimal discountMoney = BigDecimal.ZERO;// 优惠金额 指优惠了多少金额
    private BigDecimal discountPriceMoney 	= BigDecimal.ZERO;// 除单价外的优惠金额 指优惠了多少金额
    private BigDecimal discountAmount 		= BigDecimal.ZERO;// 优惠数量 指优惠了多少数量
    private BigDecimal discountFinalPrice 	= BigDecimal.ZERO;//最终优惠后的价格
    private BigDecimal discountCaclPrice 	= BigDecimal.ZERO;//促销优惠计算标准价：buyPrice、basePrice

    private PromTypeMsgResponseDTO promTypeMsgResponseDTO;// 优惠提醒信息 ifFulfillProm==true 取fulfilMsg
                                                          // 否则取noFulfilMsg
    private List<PromInfoDTO> promInfoDTOList;// 满足单品促销列表

    private boolean ifFulfillProm;// true 满足促销promId false不满足促销ID
    
    private Long ordPromId;
    
    private List<PromInfoDTO> ordPromInfoDTOList;// 满足订单促销列表
    
    private List<PromGiftDTO> promGiftDTOList;//订单赠品列表
    //初始化 返回基本信息
    public void setOrdPromDetailInfo(Long promId){
        this.setDiscountAmount(BigDecimal.ZERO);
        this.setDiscountMoney(BigDecimal.ZERO);
        this.setDiscountPrice(BigDecimal.ZERO);
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

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
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
 
    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getGroupDetail() {
        return groupDetail;
    }

    public void setGroupDetail(String groupDetail) {
        this.groupDetail = groupDetail;
    }

    public String getIsPrimeGds() {
        return isPrimeGds;
    }

    public void setIsPrimeGds(String isPrimeGds) {
        this.isPrimeGds = isPrimeGds;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff;
    }

    public String getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(String updateStaff) {
        this.updateStaff = updateStaff;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        if(discountPrice==null){
            this.discountPrice=BigDecimal.ZERO;
        }else{
            this.discountPrice = discountPrice.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
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

    public BigDecimal getDiscountFinalPrice() {
		return discountFinalPrice;
	}

	public void setDiscountFinalPrice(BigDecimal discountFinalPrice) {
		this.discountFinalPrice = discountFinalPrice;
	}

	public BigDecimal getDiscountCaclPrice() {
		return discountCaclPrice;
	}

	public void setDiscountCaclPrice(BigDecimal discountCaclPrice) {
		this.discountCaclPrice = discountCaclPrice;
	}

	public void setDiscountPriceMoney(BigDecimal discountPriceMoney) {
        if(discountPriceMoney==null){
            this.discountPriceMoney=BigDecimal.ZERO;
        }else{
            this.discountPriceMoney = discountPriceMoney.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }
    
    public PromTypeMsgResponseDTO getPromTypeMsgResponseDTO() {
        return promTypeMsgResponseDTO;
    }

    public void setPromTypeMsgResponseDTO(PromTypeMsgResponseDTO promTypeMsgResponseDTO) {
        this.promTypeMsgResponseDTO = promTypeMsgResponseDTO;
    }

    public boolean isIfFulfillProm() {
        return ifFulfillProm;
    }

    public void setIfFulfillProm(boolean ifFulfillProm) {
        this.ifFulfillProm = ifFulfillProm;
    }

    public List<PromInfoDTO> getPromInfoDTOList() {
        return promInfoDTOList;
    }

    public void setPromInfoDTOList(List<PromInfoDTO> promInfoDTOList) {
        this.promInfoDTOList = promInfoDTOList;
    }

    public Long getOrdPromId() {
        return ordPromId;
    }

    public void setOrdPromId(Long ordPromId) {
        this.ordPromId = ordPromId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
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

    public List<PromInfoDTO> getOrdPromInfoDTOList() {
        return ordPromInfoDTOList;
    }

    public void setOrdPromInfoDTOList(List<PromInfoDTO> ordPromInfoDTOList) {
        this.ordPromInfoDTOList = ordPromInfoDTOList;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
    }

    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(String skuInfo) {
        this.skuInfo = skuInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

}
